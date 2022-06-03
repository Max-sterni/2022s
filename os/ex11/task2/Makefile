CFLAGS = -g -Wall -Wextra -std=c11

all: square.so half.so iterate.so map_number

map_number: map_number.c
	gcc $(CFLAGS) -o map_number map_number.c -ldl

square.so: square.c
	gcc $(CFLAGS) -shared -o square.so square.c

half.so: half.c
	gcc $(CFLAGS) -shared -o half.so half.c

iterate.so: iterate.c
	gcc $(CFLAGS) -shared -o iterate.so iterate.c
