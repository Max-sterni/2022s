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
	if (memory == NULL)
	{
		fprintf(stderr, "Memory allocator not yet initialized\n");
		exit(EXIT_FAILURE);
	}
	// Size guard
	if (size > BLOCK_SIZE)
	{
		return NULL;
	}

	if(head == NULL){
		return NULL;
	}

	void *output = (void *)head + sizeof(struct mheader_t);
	struct mheader_t *tmp = head->next_block;
	memset(head, 0, sizeof(struct mheader_t));
	head = tmp;

	return output;
}

void my_free(void *ptr)
{
	// Convenience
	struct mheader_t *freed_header = (struct mheader_t *)(ptr - sizeof(struct mheader_t));
	printf("\nFreeing header at %p\n\n", freed_header);
	if (memory == NULL)
	{
		fprintf(stderr, "Memory allocator not yet initialized\n");
		exit(EXIT_FAILURE);
	}

	// When the pointer is before the head
	if (head == NULL || freed_header < head)
	{
		(freed_header)->next_block = head;
		head = ptr - sizeof(struct mheader_t);
		return;
	}
	else
	{
		struct mheader_t *current = head;

		// When the pointer is in the list
		while (current->next_block != NULL)
		{
			// Because the pointer is definitely bigger then current
			if (freed_header < current->next_block)
			{
				freed_header->next_block = current->next_block;
				current->next_block = freed_header;
				return;
			}
			current = current->next_block;
		}

		// When the pointer is at the end of the list
		current->next_block = ptr;
		freed_header->next_block = NULL;
	}
}

void my_allocator_init(size_t size)
{
	if (memory != NULL)
	{
		fprintf(stderr, "Memory allocator already initialized\n");
		exit(EXIT_FAILURE);
	}

	size_t absolute_block_size = BLOCK_SIZE + sizeof(struct mheader_t);

	size_t block_amount = size / absolute_block_size;
	if (size % BLOCK_SIZE != 0)
	{
		block_amount++;
	}

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

void my_allocator_destroy()
{
	if (memory == NULL)
	{
		fprintf(stderr, "Memory allocator not yet initialized\n");
		exit(EXIT_FAILURE);
	}
	munmap(memory, total_size);
}
