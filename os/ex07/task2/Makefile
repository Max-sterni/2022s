CFLAGS = -O2 -Werror -Wextra -std=c11 -pthread 

all: task2 

clean:
	rm task2 

.PHONY: all clean

task2: task2.c
	gcc $(CFLAGS) -o task2 task2.c -DUSE_MY_MUTEX=0

