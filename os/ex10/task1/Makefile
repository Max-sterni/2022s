CFLAGS = -Wall -g

all:  mymalloc.o test clean

.PHONY: all clean

test: test.c mymalloc.o
	gcc $(CFLAGS) -o test test.c mymalloc.o

mymalloc.o: mymalloc.c
	gcc $(CFLAGS) -c mymalloc.c

clean:
	rm *.o