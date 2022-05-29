#include <stdlib.h>
#include <stdio.h>

#include "mymalloc.h"

#define BLOCK_NUMBER 64

int main(void){
	my_allocator_init(BLOCK_SIZE * BLOCK_NUMBER);	
	return EXIT_SUCCESS;
}
