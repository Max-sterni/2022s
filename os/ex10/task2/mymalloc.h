#ifndef _MYALLOC_H_
#define _MYALLOC_H_

void * my_malloc(size_t size);

void my_free(void * ptr);

void my_allocator_init(size_t);

void my_allocator_destroy(void);

#endif
