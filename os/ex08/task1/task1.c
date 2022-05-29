#include <stdio.h>
#include <stdlib.h>

#include <pthread.h>
#include <stdatomic.h>

#include "thread_pool.h"

// 50000 was a segfault
#define NUMBER 5000

void * decrement_one(void * var);

int main(){	

	atomic_int var = ATOMIC_VAR_INIT(NUMBER);
	pthread_t threads[NUMBER];

	for(int i = 0; i < NUMBER; i++){
		pthread_create(threads + i, NULL, decrement_one, &var);
	}
	
	for(int i = 0; i < NUMBER; i++){
		pthread_join(threads[i], NULL);
	}
	
	printf("%d\n", var);	

	thread_pool pool;
	pool_create(&pool, 10);

	printf("%d\n", pool_submit(&pool, decrement_one, &var));

	pool_destroy(&pool);

	return EXIT_SUCCESS;
}

void * decrement_one(void * arg){
	atomic_int * var = arg;
	(*var)--;
	return NULL;	
}

