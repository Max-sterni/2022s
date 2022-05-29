#define _POSIX_C_SOURCE 200809L
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
#define USERNAME_SIZE 64

// Functiom headers
void *listener_thread_func(void *);
void *client_thread_func(void *arg_pointer);

// Arguments for threads
struct listener_args_t
{
    // Pointer to thread for cancelling
    pthread_t *listener_thread;
    // Socket to listen on
    int sockfd;
    // Socket adress
    struct sockaddr_in addr;
    // Threads for clients
    pthread_t *client_threads;
};

struct client_args_t
{
    // File descriptors to loop over and send the message
    int *connections;
    // File descriptor position of thread
    int index;
    // Pointer to thread for cancelling
    pthread_t *listener_thread;
};

// Atomics
atomic_int open_connections = ATOMIC_VAR_INIT(0);
atomic_bool shutting_down = ATOMIC_VAR_INIT(false);

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

    /*
    Initialization
    -----------------------------------------------------------------------------------------------
    */

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

    // Thread containers
    pthread_t *listener_thread, *client_threads;
    listener_thread = malloc(sizeof(pthread_t));
    client_threads = calloc(MAX_USER, sizeof(pthread_t));

    // Creating args struct
    struct listener_args_t *listener_args = malloc(sizeof(struct listener_args_t));
    listener_args->addr = addr;
    listener_args->sockfd = sockfd;
    listener_args->client_threads = client_threads;
    listener_args->listener_thread = listener_thread;

    /*
    Main Program
    -----------------------------------------------------------------------------------------------
    */

    // Creating listening thread
    if (pthread_create(listener_thread, NULL, listener_thread_func, listener_args) != 0)
    {
        perror("listener creation error");
        return EXIT_FAILURE;
    }
    printf("Listening on port %u\n", port);

    // -------------------------------------------------------------------------------------------------

    // Shutting down

    if (pthread_join(*listener_thread, NULL) != 0)
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
    free(listener_thread);
    free(client_threads);
    free(listener_args);
    return EXIT_SUCCESS;
}

/*
    Listener thread
    -----------------------------------------------------------------------------------------------
    */

void *listener_thread_func(void *arg_pointer)
{
    // Convenience
    struct listener_args_t *args = arg_pointer;

    // Accept initializing
    socklen_t socklen = sizeof(args->addr);

    // Setting up info for clients
    struct client_args_t *client_args;
    int all_connections = 0;
    int *connections = calloc(MAX_USER, sizeof(int));
    memset(connections, 0, MAX_USER * sizeof(int));

    // Connection loop
    while (1)
    {
        // Connect new user and create file descriptor
        connections[all_connections] = accept(args->sockfd, (struct sockaddr *)&args->addr, &socklen);

        if (connections[all_connections] < 0)
        {
            perror("connection error");
            exit(EXIT_FAILURE);
        }

        // Initializing the client thread
        client_args = malloc(sizeof(struct client_args_t));

        client_args->listener_thread = args->listener_thread;
        client_args->connections = connections;
        client_args->index = all_connections;

        if (pthread_create(args->client_threads + all_connections, NULL, client_thread_func, client_args) != 0)
        {
            perror("listener creation error");
            exit(EXIT_FAILURE);
        }

        open_connections++;
        all_connections++;

        client_args = NULL;
    }

    return NULL;
}

/*
    Client thread
    -----------------------------------------------------------------------------------------------
    */

void *client_thread_func(void *arg_pointer)
{
    // Convinience
    struct client_args_t *args = arg_pointer;
    int myconnection = args->connections[args->index];

    // Initializing
    char buffer[MSG_SIZE];
    char username[USERNAME_SIZE];

    // Initiate username
    read(myconnection, username, USERNAME_SIZE);

    // Send connect message to all connected users
    for (size_t i = 0; i < MAX_USER; i++)
    {
        if (args->connections[i] != 0)
        {
            dprintf(args->connections[i], "%s connected\n", username);
        }
    }

    // Chat loop
    while (1)
    {
        // Reset buffer
        memset(buffer, '\0', MSG_SIZE);

        // Read next message if it is EOF or error disconnect the user
        if (read(myconnection, buffer, MSG_SIZE) <= 0)
        {
            open_connections--;
            args->connections[args->index] = 0;

            // Send disconnect message to all connected users
            for (size_t i = 0; i < MAX_USER; i++)
            {
                if (args->connections[i] != 0)
                {
                    dprintf(args->connections[i], "%s disconnected\n", username);
                }
            }
            break;
        }

        // If user is first to call shutdown
        if (strcmp("/shutdown\n", buffer) == 0 && !shutting_down)
        {
            // Initiate shutdown and cancel listener thread
            shutting_down = true;
            pthread_cancel(*(args->listener_thread));

            // Send shutdown message to all connected users
            for (size_t i = 0; i < MAX_USER; i++)
            {
                if (args->connections[i] != 0)
                {
                    dprintf(args->connections[i], "Server is shutting down. Waiting for %d clients to disconnect.\n", open_connections);
                }
            }
        }

        // If server not shutting down send message
        if (!shutting_down)
        {
            // Send message to all connected users
            for (size_t i = 0; i < MAX_USER; i++)
            {
                // If connection is open and not myconnection
                if (args->connections[i] != 0 && !(args->connections[i] == myconnection))
                {
                    dprintf(args->connections[i], "%s: %s", username, buffer);
                }
            }
        }
    }

    return NULL;
}