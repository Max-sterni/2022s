CFLAGS = -std=c11 -Wall -Wextra -Wpedantic -pthread
.PHONY: all

all: temp_server temp_client

.PHONY: clean

clean:
	$(RM) temp_server temp_client

temp_server: temp_server.c
	$(CC) $(CFLAGS) -o $@ $^


temp_client: temp_client.c
	$(CC) $(CFLAGS) -o $@ $^