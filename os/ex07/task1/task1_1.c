#include <stdio.h>
#include <stdlib.h>

#include <pthread.h>

#define INTEGER_VALUE 100000
#define THREAD_NUMBER 1000
#define ITERATIONS 10000

void * thread_function(void *);
int X = INTEGER_VALUE;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

int main(){
	
	// Initializing the threads
	pthread_t threads[THREAD_NUMBER];	
	for(int i = 0; i < THREAD_NUMBER; i++){
		if(pthread_create(threads + i, NULL, thread_function, NULL) != 0){
			fprintf(stderr, "Thread error");
			return EXIT_FAILURE;
		}
	}	

	
	// End their lives
	for(int i = 0; i < THREAD_NUMBER; i++){
		if(pthread_join(threads[i], NULL) != 0){
			fprintf(stderr, "Thread join error");
			return EXIT_FAILURE;
		}

	}	
	printf("X = %d\n", X);

	return EXIT_SUCCESS;
}



void * thread_function(void * unused){
	
	for(int i = 0; i < ITERATIONS; i++){
		pthread_mutex_lock(&mutex);
		X--;
		pthread_mutex_unlock(&mutex);
	}

	return NULL;
}
