#include <stdlib.h>
#include <stdio.h>
#include <sys/mman.h>
#include <string.h>
#include <pthread.h>

#include "mymalloc.h"

pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

struct mheader_t
{
	struct mheader_t *next_block;
	size_t block_size;
};

struct mheader_t *head;
void *memory = NULL;
size_t total_size;

void *my_malloc(size_t size)
{
	if (memory == NULL)
	{
		fprintf(stderr, "Memory allocator not yet initialized\n");
		exit(EXIT_FAILURE);
	}

	if (head == NULL)
	{
		return NULL;
	}

	pthread_mutex_lock(&mutex);
	// Choose block
	struct mheader_t *current = head, *bestfit = NULL, * last = NULL;
	while (current->next_block != NULL)
	{
		last = current;
		// If the size is the same choose the block
		if (current->block_size == size)
		{
			bestfit = current;
			break;
		}
		// If there is no bestfit yet and size is smaller then block
		// choose block as best fit for now
		if (bestfit == NULL)
		{
			if (current->block_size > size)
			{
				bestfit = current;
			}
			continue;
		}

		// If the current block is a better fit
		else if (current->block_size < bestfit->block_size && current->block_size > size)
		{
			bestfit = current;
			continue;
		}
	}

	// If there was no fit return NULL
	if(bestfit == NULL){
		return NULL;
	}

	// If first one was a perfect fit
	if(current == last){
		head = current->next_block;
		if(head != NULL){
			
		}
	}

	void *output = (void *)head + sizeof(struct mheader_t);
	struct mheader_t *tmp = head->next_block;
	memset(head, 0, sizeof(struct mheader_t));
	head = tmp;
	pthread_mutex_lock(&mutex);

	return output;
}

void my_free(void *ptr)
{
	pthread_mutex_lock(&mutex);
	// Convenience
	struct mheader_t *freed_header = (struct mheader_t *)(ptr - sizeof(struct mheader_t));
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
		pthread_mutex_unlock(&mutex);
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
				pthread_mutex_unlock(&mutex);
				return;
			}
			current = current->next_block;
		}

		// When the pointer is at the end of the list
		current->next_block = ptr;
		freed_header->next_block = NULL;
		pthread_mutex_unlock(&mutex);
	}
}

void my_allocator_init(size_t size)
{
	pthread_mutex_lock(&mutex);
	if (memory != NULL)
	{
		fprintf(stderr, "Memory allocator already initialized\n");
		exit(EXIT_FAILURE);
	}

	total_size = size;

	// Get the memory pointer
	memory = mmap(NULL, size,
				  PROT_EXEC | PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, 0, 0);
	memset(memory, 0, total_size);
	// Initialize the memory
	struct mheader_t header;

	header.next_block = NULL;
	head->block_size = size - sizeof(struct mheader_t);

	memcpy(memory, &header, sizeof(struct mheader_t));
	head = memory;
	pthread_mutex_unlock(&mutex);
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
