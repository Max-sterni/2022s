// Programm aus Exercise 9 Task 3 Kopiert

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
#define MAX_REQUESTS 5
#define USERNAME_SIZE 64

// Functiom headers
void *listener_thread_func(void *);
void *client_thread_func(void *arg_pointer);

// Arguments for threads
struct listener_args_t
{
    // Socket to listen on
    int sockfd;
    // Socket adress
    struct sockaddr_in addr;
    // Threads for clients
    pthread_t *client_threads;
};

struct client_args_t
{
    // File descriptors for the connection
    int connection;
    // Room infos
    struct room_t **rooms;
    // Id
    int index;
};

struct room_t
{
    char *name;
    double temperature;
};

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
    client_threads = calloc(MAX_REQUESTS, sizeof(pthread_t));

    // Creating args struct
    struct listener_args_t *listener_args = malloc(sizeof(struct listener_args_t));
    listener_args->addr = addr;
    listener_args->sockfd = sockfd;
    listener_args->client_threads = client_threads;

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

    for (size_t i = 0; i < MAX_REQUESTS; i++)
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

    // Initializing info for clients
    struct client_args_t *client_args;
    int client_fd;
    struct room_t **rooms = calloc(MAX_REQUESTS, sizeof(struct room_t *));
    int connections_amount = 0;

    // Connection loop
    for (int i = 0; i < MAX_REQUESTS; i++)
    {
        // Connect new user and create file descriptor
        client_fd = accept(args->sockfd, (struct sockaddr *)&args->addr, &socklen);

        if (client_fd < 0)
        {
            perror("connection error");
            exit(EXIT_FAILURE);
        }

        // Initializing the client thread
        client_args = malloc(sizeof(struct client_args_t));
        client_args->connection = client_fd;
        client_args->rooms = rooms;
        client_args->index = connections_amount;

        if (pthread_create(args->client_threads + connections_amount, NULL, client_thread_func, client_args) != 0)
        {
            perror("listener creation error");
            exit(EXIT_FAILURE);
        }

        // Reinitializing
        client_args = NULL;
        connections_amount++;
    }

    for (int i = 0; i < MAX_REQUESTS; i++)
    {
        if (rooms != NULL)
        {
            free(rooms[i]);
        }
    }
    free(rooms);

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
    int myconnection = args->connection;

    // Initializing
    char buffer[MSG_SIZE];

    memset(buffer, 0, MSG_SIZE);

    read(myconnection, buffer, MSG_SIZE);
    if (strncmp(buffer, "set", 3) == 0)
    {
        struct room_t * room = malloc(sizeof(struct room_t));
        char * name = malloc(sizeof(MSG_SIZE));
        double temp_buffer;

        // Init name
        memset(buffer, 0, MSG_SIZE);
        read(myconnection, buffer, MSG_SIZE);
        strcpy(name, buffer);
        room->name = buffer;
        
        // Init temp
        read(myconnection, buffer, MSG_SIZE);
        temp_buffer = strtod(buffer, NULL);
        room->temperature = temp_buffer;
        
        printf("Setting temperature for %s: %lfC\n",room->name ,room->temperature);

        args->rooms[args->index] = room;
    }

    return NULL;
}