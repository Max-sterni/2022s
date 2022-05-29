#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/wait.h>
#include <string.h>

#define BUFFER_SIZE 4096

int main()
{
    // Create first Pipe
    int grep_pipefd[2];
    if (pipe(grep_pipefd) != 0)
    {
        fprintf(stderr, "Pipe error");
    }

    // Program split ls
    int pid1 = fork();
    if (pid1 == 0)
    {
        // Close unused pipes
        close(grep_pipefd[0]);

        // Write output of ls to the pipe
        dup2(grep_pipefd[1], STDOUT_FILENO);
        close(grep_pipefd[1]);

        execlp("ls", "ls", NULL);
    }

    // Create second pipe
    int wc_pipefd[2];
    if (pipe(wc_pipefd) != 0)
    {
        fprintf(stderr, "Pipe error");
    }

    // Programm split grep
    int pid2 = fork();
    if (pid2 == 0)
    {

        // Write output to input
        dup2(grep_pipefd[0], STDIN_FILENO);
        dup2(wc_pipefd[1], STDOUT_FILENO);

        // Close pipes why does this work
        close(grep_pipefd[1]);
        close(grep_pipefd[0]);
        close(wc_pipefd[0]);
        close(wc_pipefd[1]);

        execlp("grep", "grep", "-v", "var", NULL);
    }

    // Programm split wc
    int pid3 = fork();
    if (pid3 == 0)
    {

        // Write output to input
        dup2(wc_pipefd[0], STDIN_FILENO);

        // Close pipes why does this work
        close(grep_pipefd[1]);
        close(grep_pipefd[0]);
        close(wc_pipefd[0]);
        close(wc_pipefd[1]);

        execlp("wc", "wc", "-l", NULL);
    }

    // Close pipes
    close(grep_pipefd[1]);
    close(grep_pipefd[0]);
    close(wc_pipefd[0]);
    close(wc_pipefd[1]);

    // Why does this work
    waitpid(pid1, NULL, 0);
    waitpid(pid2, NULL, 0);
    waitpid(pid3, NULL, 0);

    return EXIT_SUCCESS;
}