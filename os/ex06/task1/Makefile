CFLAGS = -g -Wall -Wextra -Wpedantic -Werror -std=c11 -pthread 

all: task1a task1b

clean:
	rm task1a task1b

.PHONY: all clean

task1a: task1a.c
	gcc $(CFLAGS) -o task1a task1a.c 

task1b: task1b.c
	gcc $(CFLAGS) -o task1b task1b.c 
