#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <inttypes.h>
#include <sys/wait.h>

double mc_pi(int64_t S);

int main(int argc, char const *argv[])
{
    if (argc != 3)
    {
        return EXIT_FAILURE;
    }

    int n = atoi(argv[1]);
    int s = atoi(argv[2]);

    pid_t *pids = malloc(sizeof(pid_t) * n);

    for (int i = 0; i < n; i++)
    {
        pids[i] = fork();
        if (pids[i] < 0)
        {
            return EXIT_FAILURE;
        }
        else if (pids[i] == 0)
        {
            srand(getpid());
            printf("Child %d with pid = %d calculated %lf\n", i + 1, getpid(), mc_pi(s));
            exit(EXIT_SUCCESS);
        }
    }

    int status;
    while (n > 0)
    {
        wait(&status);
        --n;
    }

    free(pids);

    return EXIT_SUCCESS;
}

double mc_pi(int64_t S)
{
    int64_t in_count = 0;
    for (int64_t i = 0; i < S; ++i)
    {
        const double x = rand() / (double)RAND_MAX;
        const double y = rand() / (double)RAND_MAX;
        if (x * x + y * y <= 1.f)
        {
            in_count++;
        }
    }
    return 4 * in_count / (double)S;
}