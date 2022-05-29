CFLAGS = -Wall -Wpedantic -Wextra -std=c11 -g

all: server client


server: server.c
	gcc $(CFLAGS) -o server server.c -pthread

client: client.c
	gcc $(CFLAGS) -o client client.c -pthread

clean:	
	rm server client
