#include <stdlib.h>
#include <stdio.h>

#include "mymalloc.h"

#define BLOCK_NUMBER 16

int main(void){
	my_allocator_init(BLOCK_NUMBER * BLOCK_SIZE);
	test();
	int * block1 = my_malloc(BLOCK_SIZE);
	int * block2 = my_malloc(BLOCK_SIZE);
	int * block3 = my_malloc(BLOCK_SIZE);
	int * block4 = my_malloc(BLOCK_SIZE);
	my_free(block1);
	my_free(block3);
	my_free(block2);
	my_free(block4);
	test();
	
	my_allocator_destroy();

	return EXIT_SUCCESS;
}
