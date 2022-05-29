CC = gcc

CFLAGS = -std=c11 -Wall -Wextra -Wpedantic
all: piAprox

piAprox: piAprox.c
	$(CC) -o $@ $< $(CFLAGS)

.PHONY: clean 

clean:
	@rm -rf *.o
