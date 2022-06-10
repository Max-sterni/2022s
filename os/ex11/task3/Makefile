CFLAGS = -g -Wall -Wextra -std=c11

malloc_spy.so: malloc_spy.c
	gcc $(CFLAGS) -shared -o malloc_spy.so malloc_spy.c -ldl 

clean: 
	rm *.so
