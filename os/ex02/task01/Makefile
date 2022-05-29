CC       = gcc

CFLAGS   = -std=c11 -Wall -Wextra -Wpedantic
all: read_my_env_var

read_my_env_var: read_my_env_var.c
	$(CC) -o $@ $< $(CFLAGS)

.PHONY: clean 

clean:
	@rm -rf *.o