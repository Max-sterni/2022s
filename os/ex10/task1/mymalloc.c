#include <stdlib.h>
#include <stdio.h>
#include <sys/mman.h>

#include "mymalloc.h"

struct mblock_head_t{
	struct mblock_head_t * next_block;
};

void * memory = NULL;

void * my_malloc(size_t size){
	return NULL;
}

void my_free(void * ptr){

}

void my_allocator_init(size_t size){
	if(memory == NULL){
		size_t block_amount = size / BLOCK_SIZE;
		memory =  mmap(NULL, block_amount * (BLOCK_SIZE + sizeof(struct mblock_head_t)), PROT_EXEC | PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, 0, 0);
		printf("%pTest\n", memory);
	}		
}
