#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#include <sys/types.h>
#include <pthread.h>

int my_global = 1;
int create_numbers(char ** argv, int argc, int * arr);
void * thread_function(void *);

struct thread_arg{
	int result;
	int i;
	int * numbers;	
};

int main(int argc, char ** argv){
	
	if(argc < 2){
		fprintf(stderr, "More arguments needed\n");
		return EXIT_FAILURE;
	}	

	int * numbers = malloc(sizeof(int) * (argc - 1)), n = argc - 1;
	
	if(create_numbers(argv, argc, numbers) != 0){
		fprintf(stderr, "Invalid input\n");	
		return EXIT_FAILURE;
	}
	
	pthread_t threads[n];
	struct thread_arg * args = malloc(sizeof(struct thread_arg) * n);

	for(int i = 0; i < n; i++){
		args[i].i = i + 1;
		args[i].numbers = numbers;
		if(pthread_create(threads + i, NULL, thread_function, args + i) != 0){
			fprintf(stderr, "Thread failure\n");
			return EXIT_FAILURE;
		}
	}

	for(int i = 0; i < n; i++){
		pthread_join(threads[i], NULL);
		printf("sum%d = %d\n", i, args[i].result);
	}

	free(args);
	free(numbers);
	return EXIT_SUCCESS;
}

int create_numbers(char ** argv, int argc, int * arr){
	for(int i = 1; i < argc; i++){
		arr[i - 1] = atoi(argv[i]);
		if(arr[i - 1] == 0 && argv[i][0] != '0'){
			return EXIT_FAILURE;
		}	
	}
	return EXIT_SUCCESS;
}

void * thread_function(void * arg){
	struct thread_arg * targ = arg;
	targ->result = 0;
	for(int i = 0; i < targ->i; i++){
		targ->result += targ->numbers[i];
	}
	
	return NULL;
}
