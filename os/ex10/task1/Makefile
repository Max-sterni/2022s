CFLAGS = -Wall -O2

all:  mymalloc.o allocator_tests.o membench.o test clean

.PHONY: all clean

test: test.c mymalloc.o
	gcc $(CFLAGS) -o test test.c mymalloc.o allocator_tests.o membench.o -pthread

mymalloc.o: mymalloc.c
	gcc $(CFLAGS) -c mymalloc.c

membench.o: membench.c
	gcc $(CFLAGS) -c membench.c -pthread

allocator_tests.o: allocator_tests.c
	gcc $(CFLAGS) -c allocator_tests.c

clean:
	rm *.o