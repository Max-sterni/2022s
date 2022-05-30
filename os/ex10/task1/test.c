#include <stdlib.h>
#include <stdio.h>

#include "mymalloc.h"

#define BLOCK_NUMBER 3

int main(void){
	my_allocator_init(64);
	test();
	int * array = my_malloc(2 * sizeof(int));
	test();
	my_free(array);
	test();
	
	return EXIT_SUCCESS;
}
