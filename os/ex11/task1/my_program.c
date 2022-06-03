#include <stdlib.h>
#include <stdio.h>

int main(int argc, char ** argv){
	
	// Argument guard 
	if(argc != 2){
		fprintf(stderr, "myprogram <number>\n");
		return EXIT_FAILURE;
	}
	
	int n = atoi(argv[1]);
	
	if(n == 0 && argv[1][0] != '0'){
		fprintf(stderr, "invalid input\n");
		return EXIT_FAILURE;
	}

	printf("%d\n", n * n + 1);

	return EXIT_SUCCESS;
}
