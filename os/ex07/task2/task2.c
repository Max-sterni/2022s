#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include <sys/types.h>
#include <pthread.h>
#include <stdbool.h>
#include <stdatomic.h>

#include "myqueue.h"

#define NUM_THREADS 2 
#define NUMBER_OF_ONES 10000000

#if USE_MY_MUTEX
	#define MUTEX_LOCK my_mutex_lock(&atomic);
	#define MUTEX_UNLOCK my_mutex_unlock(&atomic);
#else 
	#define MUTEX_LOCK pthread_mutex_lock(&mutex);
	#define MUTEX_UNLOCK pthread_mutex_unlock(&mutex);
#endif

void * thread_function_producer(void * voidpointer);
void * thread_function_consumer(void * voidpointer);

void my_mutex_lock(atomic_flag *);
void my_mutex_unlock(atomic_flag *);

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;   
myqueue queue;
atomic_flag atomic = ATOMIC_FLAG_INIT;

int main(){
	pthread_t thread_id[NUM_THREADS];
	myqueue_init(&queue);

	if(pthread_create(thread_id + 0, NULL, thread_function_consumer, NULL) != 0){
		fprintf(stderr, "Thread creation error\n");
		return EXIT_FAILURE;
	}

	if(pthread_create(thread_id + 1, NULL, thread_function_producer, NULL) != 0){
		fprintf(stderr, "Thread creation error\n");
		return EXIT_FAILURE;
	}

	
	for(int i = 0; i < NUM_THREADS; i++){
		if(pthread_join(thread_id[i], NULL) != 0){
			fprintf(stderr, "Thread joining error\n");
			return EXIT_FAILURE;
		}
	}

	return EXIT_SUCCESS;
}

void * thread_function_consumer(void * voidpointer){
	int sum = 0;
	int val;	
	
	do {
		MUTEX_LOCK;
		if(!myqueue_is_empty(&queue)){	
			val = myqueue_pop(&queue);
			sum += val;
		}
		MUTEX_UNLOCK;
	}	
	while(val);

	printf("Sum = %d\n", sum);

	return NULL;
}

	
void * thread_function_producer(void * voidpointer){
	for(int i = 0; i < NUMBER_OF_ONES; i++){
		MUTEX_LOCK;
		myqueue_push(&queue, 1);
		MUTEX_UNLOCK;
	}

	MUTEX_LOCK;
	myqueue_push(&queue, 0);
	MUTEX_UNLOCK;

	return NULL;
}

	
void my_mutex_lock(atomic_flag * atom){
	
}
