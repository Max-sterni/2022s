#include <stdio.h>
#include <stdlib.h>

#include <dlfcn.h>
#include <string.h>

#define BUF_SIZE 128

int main(int argc, char ** argv){
	
	// Argument guard 
	if(argc < 2){
		perror("invalid arguments");
		exit(EXIT_FAILURE);
	}
	
	int n = atoi(argv[1]);
	
	printf("n = %d\n", n);	
	if(n == 0 && argv[1][0] != '0'){
		perror("invalid number");
		exit(EXIT_FAILURE);
	}

	// Get the handles
	void * handles[argc - 2];

	for(int i = 0; i < argc - 2; i++){
		handles[i] = dlopen(argv[i + 2], RTLD_NOW);
		printf("%s\n", argv[i+2]);
		if(handles[i] == NULL){
			perror("Invalid lib");
			exit(EXIT_FAILURE);
		}
	}

	// Get the functions
	int(*func_pointer)(int);
	char buffer[BUF_SIZE];	

	for(int i = 0; i < argc - 2; i++){
		memset(buffer, 0, BUF_SIZE);
		strncpy(buffer, argv[i + 2] + 2, strlen(argv[i + 1] + 2) - 3);
		func_pointer = dlsym(handles[i], buffer);
		
		// Error guard
		if(func_pointer == NULL){
			perror("Library error");
			exit(EXIT_FAILURE);
		}

		n = func_pointer(n);
		
	}

	printf("%d\n", n);
	
	return EXIT_SUCCESS;
}
