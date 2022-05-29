
CFLAGS = -Werror -Wall -g -pthread -pthread

all: task3_print task3_print_server

task3_print: task3_print.c
	gcc $(CFLAGS) task3_print.c -o task3_print -lrt

task3_print_server: task3_print_server.c
	gcc $(CFLAGS) task3_print_server.c -o task3_print_server -lrt
