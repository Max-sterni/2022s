#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include <sys/types.h>
#include <pthread.h>
#include <stdbool.h>

#include "myqueue.h"

#define NUM_THREADS 500
#define NUMBER_OF_ONES 100000

void * thread_function();
pthread_mutex_t mutex;
pthread_cond_t condition_var;

struct thread_args{
	int sum;
	myqueue * queue;
};

int main(){
	pthread_t thread_id[NUM_THREADS];
	struct thread_args * args = calloc(NUM_THREADS, sizeof(struct thread_args));	

	// initializing 	
	myqueue queue;
	myqueue_init(&queue);

	pthread_mutex_init(&mutex, NULL);   
	pthread_cond_init(&condition_var, NULL);

	for(int i = 0; i < NUM_THREADS; i++){
		args[i].queue = &queue;
		if(pthread_create(thread_id + i, NULL, thread_function, (args + i)) != 0){
			fprintf(stderr, "Thread creation error\n");
			return EXIT_FAILURE;
		}
	}

	for(int i = 0; i < NUMBER_OF_ONES; i++){
		pthread_mutex_lock(&mutex);
		myqueue_push(&queue, 1);
		pthread_cond_broadcast(&condition_var);
		pthread_mutex_unlock(&mutex);
	}

	for(int i = 0; i < NUM_THREADS; i++){
		pthread_mutex_lock(&mutex);
		myqueue_push(&queue, 0);
		pthread_cond_signal(&condition_var);
		pthread_mutex_unlock(&mutex);
	}
	
	for(int i = 0; i < NUM_THREADS; i++){
		if(pthread_join(thread_id[i], NULL) != 0){
			fprintf(stderr, "Thread joining error\n");
			return EXIT_FAILURE;
		}
	}

	int final = 0;	
	for(int i = 0; i < NUM_THREADS; i++){
		printf("Consumer %d sum: %d\n", i, args[i].sum);
		final += args[i].sum;		
	}	
		
	printf("Final sum: %d\n", final);
	free(args);
	return EXIT_SUCCESS;
}

void * thread_function(void * voidpointer){
	int val = -1;
	struct thread_args * args = voidpointer;

	while(val != 0){
		pthread_mutex_lock(&mutex);
		while(myqueue_is_empty(args->queue)){
			pthread_cond_wait(&condition_var, &mutex);
		}
		val = myqueue_pop(args->queue);
		args->sum += val;
		pthread_mutex_unlock(&mutex);
		
	}
	return NULL;
}
