CFLAGS = -Wall -g

all:  mymalloc.o allocator_tests.o test clean

.PHONY: all clean

test: test.c mymalloc.o
	gcc $(CFLAGS) -o test test.c mymalloc.o allocator_tests.o

mymalloc.o: mymalloc.c
	gcc $(CFLAGS) -c mymalloc.c

allocator_tests.o: allocator_tests.c
	gcc $(CFLAGS) -c allocator_tests.c

clean:
	rm *.o