#include <stdlib.h>
#include <stdio.h>
#include <sys/mman.h>
#include <string.h>

#include "mymalloc.h"

struct mheader_t
{
	struct mheader_t *next_block;
};

struct mheader_t *head;
void *memory = NULL;
int tests = 0;
size_t total_size;

void *my_malloc(size_t size)
{
	// Size guard
	if (size > BLOCK_SIZE)
	{
		return NULL;
	}

	void *output = (void *) head + sizeof(struct mheader_t);
	head = head->next_block;

	return output;
}

void my_free(void *ptr)
{
	// When the pointer is before the head
	if (ptr < (void *) head)
	{
		((struct mheader_t *)ptr)->next_block = head;
		head = ptr - sizeof(struct mheader_t);
		return;
	}
	// else
	// {
	// 	struct mheader_t *current = head;

	// 	// When the pointer is in the list
	// 	while (current->next_block != NULL)
	// 	{
	// 		// Because the pointer is definitely bigger then current
	// 		if (ptr < (void *) current->next_block)
	// 		{
	// 			((struct mheader_t *)ptr)->next_block = current->next_block;
	// 			current->next_block = ptr;
	// 			return;
	// 		}
	// 	}

	// 	// When the pointer is at the end of the list
	// 	current->next_block = ptr;
	// 	((struct mheader_t *)ptr)->next_block = NULL;
	// }
}

void my_allocator_init(size_t size)
{
	if (memory == NULL)
	{
		size_t block_amount = size / BLOCK_SIZE;
		if(size % BLOCK_SIZE != 0){
			block_amount++;
		}
		size_t absolute_block_size = BLOCK_SIZE + sizeof(struct mheader_t);
		printf("%ld %ld\n", block_amount, sizeof(struct mheader_t));
		total_size = block_amount * absolute_block_size;
		// Get the memory pointer
		memory = mmap(NULL, block_amount * absolute_block_size,
					  PROT_EXEC | PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, 0, 0);
		memset(memory, 0, total_size);
		// Initialize the memory
		struct mheader_t header;
		for (size_t i = 0; i < block_amount; i++)
		{
			if (i + 1 == block_amount)
			{
				header.next_block = NULL;
			}
			else
			{
				header.next_block = memory + (i + 1) * absolute_block_size;
			}
			memcpy(memory + i * absolute_block_size, &header, sizeof(struct mheader_t));
		}
		head = memory;
	}
}

void test()
{
	for (size_t i = 0; i < total_size / sizeof(void **); i++)
	{
		printf("%p: %p\n", (void **) memory + i, ((void **) memory)[i]);
	}

	struct mheader_t *current = head;
	printf("Test: %d Memory: %p Head: %p\n",++tests, memory, head);
	int i = 1;
	while(current != NULL)
	{
		printf("%d Current: %p Next: %p\n", i++, current, current->next_block);
		current = current->next_block;
		
	}
}

