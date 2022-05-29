CC = gcc

CFLAGS = -std=c11 -Wall -Wextra -Wpedantic
all: even

even: even.c
	$(CC) -o $@ $< $(CFLAGS)

.PHONY: clean 

clean:
	@rm -rf *.o
