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
#define USERNAME_SIZE 64

#define SHUTDOWN(sockfd)      \
    do                        \
    {                         \
        write(sockfd, "", 0); \
        close(sockfd);        \
        return NULL;          \
    } while (0)

void *thread_func(void *);
void *read_thread_func(void *args_pointer);

struct thread_args_t
{
    int sockfd;
};

int main(int argc, char const *argv[])
{
    // Argument Guard
    if (argc != 3)
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

    char username[USERNAME_SIZE];
    strcpy(username, argv[2]);

    // Initializing socket
    int sockfd;
    struct sockaddr_in addr;
    memset(&addr, 0, sizeof(struct sockaddr_in));
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    addr.sin_addr.s_addr = htonl(INADDR_LOOPBACK);

    // Creating socket
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd == 0)
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Connect the socket

    if (connect(sockfd, (struct sockaddr *)&addr, sizeof(addr)))
    {
        perror("connection failed");
        exit(EXIT_FAILURE);
    }

    // Initiate username
    write(sockfd, username, strlen(username));

    pthread_t thread;
    pthread_t read_thread;
    struct thread_args_t thread_args;
    thread_args.sockfd = sockfd;

    if (pthread_create(&thread, NULL, thread_func, &thread_args) != 0)
    {
        perror("thread creation error");
        exit(EXIT_FAILURE);
    }

    if (pthread_create(&read_thread, NULL, read_thread_func, &thread_args) != 0)
    {
        perror("thread creation error");
        exit(EXIT_FAILURE);
    }

    if (pthread_join(thread, NULL) != 0)
    {
        perror("thread join error");
        exit(EXIT_FAILURE);
    }

    if (pthread_join(read_thread, NULL) != 0)
    {
        perror("thread join error");
        exit(EXIT_FAILURE);
    }

    return EXIT_SUCCESS;
}

void *thread_func(void *args_pointer)
{
    struct thread_args_t *args = args_pointer;
    char buffer[MSG_SIZE];

    while (1)
    {
        memset(buffer, '\0', MSG_SIZE);
        fgets(buffer, MSG_SIZE, stdin);

        if (strcmp(buffer, "/shutdown\n") == 0)
        {
            write(args->sockfd, buffer, strlen(buffer));
            SHUTDOWN(args->sockfd);
        }

        if (strcmp(buffer, "/quit\n") == 0)
        {
            SHUTDOWN(args->sockfd);
        }

        write(args->sockfd, buffer, strlen(buffer));
    }
}

void *read_thread_func(void *args_pointer)
{
    struct thread_args_t *args = args_pointer;
    char buffer[MSG_SIZE];

    while (1)
    {
        memset(buffer, '\0', MSG_SIZE);
        read(args->sockfd, buffer, MSG_SIZE);
        printf("%s", buffer);
    }
    return NULL;
}
