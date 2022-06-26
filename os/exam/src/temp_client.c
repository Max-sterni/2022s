// Programm aus Exercise 9 Task 3 Kopiert
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <string.h>
#include <stdbool.h>

#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>


#define ROOM_SIZE 128
#define COMMAND_SIZE 64
#define TEMP_SIZE 128

void *thread_func(void *);
void *read_thread_func(void *args_pointer);

struct thread_args_t
{
    int sockfd;
};

int main(int argc, char const *argv[])
{
    // Argument Guard
    if (argc < 3 || argc > 5)
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

    char command[COMMAND_SIZE];
    strcpy(command, argv[2]);
    strcat(command, "\n");

    char * room_name = malloc(ROOM_SIZE);
    char * temperature = malloc(TEMP_SIZE);

    printf("%u %s\n", port, command);

    if(argc == 4){
        strcpy(room_name, argv[3]);
        strcat(room_name, "\n");
    }

    if(argc == 5){
        strcpy(temperature, argv[4]);
        strcat(temperature, "\n");
    }

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

    // Initiate command
    write(sockfd, command, strlen(command));

    if (strncmp(command, "set", 3) == 0)
    {
        write(sockfd, room_name, strlen(room_name));
        write(sockfd, temperature, strlen(temperature));
    }
    close(sockfd);
    free(room_name);
    free(temperature);

    return EXIT_SUCCESS;
}

// void *thread_func(void *args_pointer)
// {
//     struct thread_args_t *args = args_pointer;
//     char buffer[MSG_SIZE];

//     while (1)
//     {
//         memset(buffer, '\0', MSG_SIZE);
//         fgets(buffer, MSG_SIZE, stdin);

//         if (strcmp(buffer, "/shutdown\n") == 0)
//         {
//             write(args->sockfd, buffer, strlen(buffer));
//             SHUTDOWN(args->sockfd);
//         }

//         if (strcmp(buffer, "/quit\n") == 0)
//         {
//             SHUTDOWN(args->sockfd);
//         }

//         write(args->sockfd, buffer, strlen(buffer));
//     }
// }

// void *read_thread_func(void *args_pointer)
// {
//     struct thread_args_t *args = args_pointer;
//     char buffer[MSG_SIZE];

//     while (1)
//     {
//         memset(buffer, '\0', MSG_SIZE);
//         read(args->sockfd, buffer, MSG_SIZE);
//         printf("%s", buffer);
//     }
//     return NULL;

// }
