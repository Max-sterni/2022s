#include "thread_pool.h"

#include <stdlib.h>
#include <stdio.h>

#include <pthread.h>
#include <stdatomic.h>
#include "myqueue.h"

void * idle_function(void * arg);
pthread_cond_t condition_var;
pthread_mutex_t mutex;
myqueue queue;
int * finished_jobs;

struct job{
	job_function function;
	job_arg arg;
	job_id id;
};

void pool_create(thread_pool * pool, size_t thread_amount){
	pthread_t * threads = malloc(sizeof(pthread_t) * thread_amount);	
	
	pthread_mutex_init(&mutex, NULL);   
	pthread_cond_init(&condition_var, NULL);
	myqueue_init(&queue);
	finished_jobs = malloc(thread_amount * sizeof(int));

	for(int i = 0; i < thread_amount; i++){
		if(pthread_create(threads + i, NULL, idle_function, NULL) != 0){
			perror("thread error");
			exit(EXIT_FAILURE);
		}
	}
}

job_id pool_submit(thread_pool* pool, job_function start_routine, job_arg arg){
	srand((long) arg);
	struct job * new_job = malloc(sizeof(struct job));
	new_job->function = start_routine;
	new_job->arg = arg;
	new_job->id = rand();
	myqueue_push(&queue, new_job);

	return new_job->id;
}

void pool_await(job_id id){
	
}

void pool_destroy(thread_pool* pool){
	free(pool->threads);
}

void * idle_function(void * arg){
	struct job * current_job;

	pthread_mutex_lock(&mutex);
	while(myqueue_is_empty(&queue)){
		pthread_cond_wait(&condition_var, &mutex);
	}
	current_job = myqueue_pop(&queue);
	pthread_mutex_unlock(&mutex);	
	
	

	return NULL;
}
