
CFLAGS = -Wall -Wextra -std=c11

my_program: my_program.c
	gcc $(CFLAGS) -static -o my_program my_program.c

some_library.so: some_library.c
	gcc $(CFLAGS) -o some_library.so some_library

clean:
	rm *.so my_program
