#include <stdio.h>
#include <stdlib.h>

#include <pthread.h>
#include <stdatomic.h>

#define INTEGER_VALUE 100000
#define THREAD_NUMBER 1000

void * thread_function(void *);
atomic_int X = ATOMIC_VAR_INIT(INTEGER_VALUE);

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
	
	for(int i = 0; i < INTEGER_VALUE / THREAD_NUMBER; i++){
		X--;
	}

	return NULL;
}
