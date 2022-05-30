#include <stdlib.h>
#include <stdio.h>

#include "mymalloc.h"

#define BLOCK_NUMBER 2

int main(void){
	my_allocator_init(BLOCK_NUMBER * BLOCK_SIZE);
	test();
	int * block1 = my_malloc(BLOCK_SIZE);
	int * block2 = my_malloc(BLOCK_SIZE);
	test();
	my_free(block1);
	test();
	my_free(block2);
	test();
	my_allocator_destroy();

	return EXIT_SUCCESS;
}
