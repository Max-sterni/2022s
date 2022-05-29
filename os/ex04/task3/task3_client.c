#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <poll.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

int simple_hash(char *key, int len);

int main()
{
    char * name = "Sack";
    
    //Create Path
    char * path_template = "/tmp/task3-fifo-";
    char * path = malloc(_PC_PATH_MAX);
    memset(path, 0, _PC_PATH_MAX);
    sprintf(path, "%s%u", path_template, simple_hash(name, strlen(name)));

    int pipe_fd = open(path, 'w');
    write(pipe_fd, "Hallo", 5);

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