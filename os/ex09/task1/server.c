#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <string.h>
#include <stdbool.h>

#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>

#include <pthread.h>
#include <stdatomic.h>

#define MSG_SIZE 128
#define MAX_USER 128
#define LISTENER 0
#define USERNAME_SIZE 64

// Functiom headers
void *listener_thread_func(void *);
void *client_thread_func(void *arg_pointer);

// Arguments for threads
struct listener_args_t
{
	pthread_t *listener_thread;
	int sockfd;
	struct sockaddr_in addr;
	atomic_bool *shutdown_b;
	pthread_t *client_threads;
};

struct client_args_t
{
	int connectfd;
	pthread_t *listener_thread;
	atomic_bool *shutdown_b;
};

atomic_int open_connections = ATOMIC_VAR_INIT(0);

// -------------------------------------------------------------------------------------------*

int main(int argc, char **argv)
{

	// Argument Guard
	if (argc != 2)
	{
		fprintf(stderr, "invalid number of arguments\n");
		exit(EXIT_FAILURE);
	}

	uint16_t port = (uint16_t)atoi(argv[1]);

	if (port == 0)
	{
		fprintf(stderr, "invalid argument\n");
		exit(EXIT_FAILURE);
	}

	// Initializing socket
	int sockfd;
	struct sockaddr_in addr;
	memset(&addr, 0, sizeof(struct sockaddr_in));
	addr.sin_family = AF_INET;
	addr.sin_port = htons(port);
	addr.sin_addr.s_addr = htonl(INADDR_ANY);

	// Creating socket
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd == 0)
	{
		perror("socket failed");
		exit(EXIT_FAILURE);
	}

	if (bind(sockfd, (struct sockaddr *)&addr, sizeof(addr)) < 0)
	{
		perror("bind failed");
		exit(EXIT_FAILURE);
	}

	// Listening on a socket
	if (listen(sockfd, 0) != 0)
	{
		perror("listening error");
		exit(EXIT_FAILURE);
	}

	// Initialize listener thread
	pthread_t listener_thread;
	pthread_t *client_threads = malloc(sizeof(pthread_t) * (MAX_USER));
	memset(client_threads, 0, sizeof(pthread_t) * MAX_USER);
	struct listener_args_t *listener_args = malloc(sizeof(struct listener_args_t));

	atomic_bool *shutdown_b = malloc(sizeof(atomic_bool));
	*shutdown_b = ATOMIC_VAR_INIT(false);

	listener_args->addr = addr;
	listener_args->sockfd = sockfd;
	listener_args->shutdown_b = shutdown_b;
	listener_args->client_threads = client_threads;
	listener_args->listener_thread = &listener_thread;
	// listener_args->open_connections = open_connections;

	// Creating listening thread
	if (pthread_create(&listener_thread, NULL, listener_thread_func, listener_args) != 0)
	{
		perror("listener creation error");
		return EXIT_FAILURE;
	}
	printf("Listening on port %u\n", port);

	if (pthread_join(listener_thread, NULL) != 0)
	{
		perror("join error");
		return EXIT_FAILURE;
	}

	for (size_t i = 0; i < MAX_USER; i++)
	{
		if (client_threads[i] != 0)
		{
			if (pthread_join(client_threads[i], NULL) != 0)
			{
				perror("join error");
				return EXIT_FAILURE;
			}
		}
	}

	close(sockfd);
	free(shutdown_b);
	free(client_threads);
	return EXIT_SUCCESS;
}

void *listener_thread_func(void *arg_pointer)
{
	struct listener_args_t *args = arg_pointer;

	struct client_args_t *client_args;
	socklen_t socklen = sizeof(args->addr);
	int all_connections = 0;

	// Connection loop
	while (1)
	{

		client_args = malloc(sizeof(client_args));
		client_args->connectfd = accept(args->sockfd, (struct sockaddr *)&args->addr, &socklen);

		if (client_args->connectfd < 0)
		{
			perror("connection error");
			exit(EXIT_FAILURE);
		}

		client_args->shutdown_b = args->shutdown_b;
		client_args->listener_thread = args->listener_thread;

		if (pthread_create(args->client_threads + all_connections, NULL, client_thread_func, client_args) != 0)
		{
			perror("listener creation error");
			exit(EXIT_FAILURE);
		}

		open_connections++;
		all_connections++;
	}

	return NULL;
}

void *client_thread_func(void *arg_pointer)
{
	struct client_args_t *args = arg_pointer;

	// Initializing
	char buffer[MSG_SIZE];
	char username[USERNAME_SIZE];

	// Initiate username
	read(args->connectfd, username, USERNAME_SIZE);
	printf("%s connected\n", username);

	// Chat loop
	while (1)
	{
		memset(buffer, 0, MSG_SIZE);
		
		if (read(args->connectfd, buffer, MSG_SIZE) <= 0)
		{				
			printf("%s disconnected\n", username);
			open_connections--;
			
			break;
		}

		if (strncmp("/shutdown", buffer, 9) == 0 && !*(args->shutdown_b))
		{
			*args->shutdown_b = true;
			pthread_cancel(*args->listener_thread);

			if (read(args->connectfd, buffer, MSG_SIZE) <= 0)
			{
				printf("%s disconnected\n", username);
				open_connections--;
			}

			printf("Server is shutting down. Waiting for %d clients to disconnect.\n", open_connections);
			break;
		}

		if (!*(args->shutdown_b))
		{
			printf("%s: %s", username, buffer);
		}
	}

	// thread shutdown
	close(args->connectfd);
	free(args);

	return NULL;
}
