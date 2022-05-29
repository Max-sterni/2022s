#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include <sys/types.h>
#include <pthread.h>

int my_global = 1;
void * thread_function();

int main(){
	
	switch(fork()){
		case(0):
			// Child
			my_global++;
			exit(EXIT_SUCCESS);
			break;
		case(-1):
			// Error
			fprintf(stderr, "Fork error\n");
			exit(EXIT_FAILURE);			
		default:
			// Parent
			break;
	}
	
	printf("%d\n", my_global);

	pthread_t thread_id;

	if(pthread_create(&thread_id, NULL, thread_function, NULL) != 0){
		fprintf(stderr, "Thread creation error\n");
	}
	
	
	pthread_join(thread_id, NULL);
		
	printf("%d\n", my_global);
	return EXIT_SUCCESS;
}

void * thread_function(){
	my_global++;
	return NULL;
}
