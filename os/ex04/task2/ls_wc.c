#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/wait.h>
#include <string.h>

#define BUFFER_SIZE 4096

int main()
{
    // Create Pipes
    int grep_pipefd[2];
    if (pipe(grep_pipefd) != 0)
    {
        fprintf(stderr, "Pipe error");
    }

    // Program split ls
    int pid1 = fork();
    if (pid1 == 0)
    {
        // Close read end
        close(grep_pipefd[0]);

        // Write output of ls to the pipe
        dup2(grep_pipefd[1], STDOUT_FILENO);
        close(grep_pipefd[1]);

        execlp("ls", "ls", NULL);
    }

    // Programm split grep
    int pid2 = fork();
    if (pid2 == 0)
    {

        // Write output to input
        dup2(grep_pipefd[0], STDIN_FILENO);

        // Close pipes why does this work
        close(grep_pipefd[1]);
        close(grep_pipefd[0]);

        execlp("wc", "wc", "-l", NULL);
    }

    close(grep_pipefd[0]);
    close(grep_pipefd[1]);

    // Why does this work
    waitpid(pid1, NULL, 0);
    waitpid(pid2, NULL, 0);

    return EXIT_SUCCESS;
}