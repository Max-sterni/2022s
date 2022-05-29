#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <poll.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

#define BUFFER_SIZE 4096

int simple_hash(char *key, int len);

unsigned int main(unsigned int argc, char *argv[])
{
    // Guard
    if (argc < 2)
    {
        fprintf(stderr, "invalid input\n");
        return EXIT_FAILURE;
    }

    // Create Path
    char *path_template = "/tmp/task3-fifo-";
    char *path = malloc(_PC_PATH_MAX);
    memset(path, 0, _PC_PATH_MAX);

    // Create the pipes
    unsigned int hash_list[argc - 1];
    int pipe_fd[argc - 1];
    char buffer[BUFFER_SIZE];
    for (int i = 1; i < argc; i++)
    {

        // Name the fifo
        hash_list[i - 1] = simple_hash(argv[i], strlen(argv[i]));

        // Create the fifo
        if (mkfifo(path, 0770) != 0)
        {
            fprintf(stderr, "Fifo error\n");
            return EXIT_FAILURE;
        }
        pipe_fd[i - 1] = open(path, 'r');
        printf("User %s connencted\n", argv[i]);
    }

    // Pollin
    struct pollfd poll_fd[argc - 1];
    for (int i = 0; i < argc - 1; i++)
    {
        poll_fd[i].events = POLLIN;
        poll_fd[i].fd = pipe_fd[i];
    }
    poll(poll_fd, argc - 1, -1);

    // Cleanup
    for (int i = 0; i < argc - 1; i++)
    {
        sprintf(path, "%s%u", path_template, hash_list[i - 1]);
        remove(path);
    }

    free(path);

    return EXIT_SUCCESS;
}

int simple_hash(char *key, int len)
{
    int hash, i;
    for (hash = i = 0; i < len; ++i)
    {
        hash += key[i];
        hash += (hash << 10);
        hash ^= (hash >> 6);
    }
    hash += (hash << 3);
    hash ^= (hash >> 11);
    hash += (hash << 15);
    return hash;
}