#include <stdio.h>
#include <stdlib.h>

#include <dlfcn.h>
#include <string.h>

#define BUF_SIZE 128

#define ARGUMENT_OFFSET 2
#define PREFIX_OFFSET 2
#define SUFFIX_OFFSET 3

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
	void * handles[argc - ARGUMENT_OFFSET];

	for(int i = 0; i < argc - ARGUMENT_OFFSET; i++){
		handles[i] = dlopen(argv[i + ARGUMENT_OFFSET], RTLD_NOW);
		if(handles[i] == NULL){
			perror("Invalid lib");
			exit(EXIT_FAILURE);
		}
	}

	// Get the functions
	int(*func_pointer)(int);
	char buffer[BUF_SIZE];	
	int length = 0;
	int name_beginning = 0;

	for(int i = 0; i < argc - ARGUMENT_OFFSET; i++){
		memset(buffer, 0, BUF_SIZE);

		// Find the beginning of function name
		for(size_t j = 0; j < strlen(argv[i + ARGUMENT_OFFSET]); j++){
			if(argv[i + ARGUMENT_OFFSET][j] == '/'){
				name_beginning = j + 1;
			}
		}

		// Calculate length of function name
		length = strlen(argv[i + ARGUMENT_OFFSET] + name_beginning) - SUFFIX_OFFSET;

		// Save function name in buffer
		strncpy(buffer, argv[i + ARGUMENT_OFFSET] + name_beginning, length);

		// Extract function pointer
		func_pointer = dlsym(handles[i], buffer);
		
		// Error guard
		if(func_pointer == NULL){
			perror("Library loading error");
			exit(EXIT_FAILURE);
		}

		n = func_pointer(n);
		printf("%s: %d\n", argv[i + ARGUMENT_OFFSET], n);
		
	}
	
	return EXIT_SUCCESS;
}
