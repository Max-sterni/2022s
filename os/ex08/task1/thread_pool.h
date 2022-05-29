#ifndef PTHREAD_POOL_H_

#define PTHREAD_POOL_H_

#include <pthread.h>
#include <stdio.h>

#include "myqueue.h"

typedef struct thread_pool_struct
{
	myqueue * job_queue;
	pthread_t * threads;		
} thread_pool;

void pool_create(thread_pool* pool, size_t size);

typedef int job_id; 
typedef void* (*job_function)(void*);
typedef void* job_arg;

job_id pool_submit(thread_pool* pool, job_function start_routine, job_arg arg);

void pool_await(job_id id);

void pool_destroy(thread_pool* pool);

#endif
