#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/stat.h>
#include <fcntl.h>

#include <mqueue.h>
#include <signal.h>
#include <stdbool.h>

#define MAX_MSG_SIZE 4096
#define MAX_MSGS 10

bool term = false;

void handler(int sig);

int main(int argc, char *argv[])
{

	// Initializing
	struct mq_attr attrs;

	attrs.mq_maxmsg = MAX_MSGS;
	attrs.mq_msgsize = MAX_MSG_SIZE;
	mqd_t queue = mq_open("/csaz8744", O_RDONLY | O_CREAT, S_IRUSR | S_IWUSR, &attrs);

	char *buffer = malloc(MAX_MSG_SIZE);
	unsigned int *prio = malloc(sizeof(unsigned int));
	ssize_t size;
	
	//Setup the signal handler
	struct sigaction catch;
	catch.sa_handler = handler;
	
	sigaction(SIGINT, &catch, NULL);
	
	// Receive loop for printing	
	while(!term){
		size = mq_receive(queue, buffer, MAX_MSG_SIZE, prio);	
		printf("Server print job with priority %u:\n", *prio);
		for(ssize_t i = 0; i < size; i++){
			putchar(buffer[i]);
			fflush(stdout);
			usleep(200 * 1000);
		}
		printf("\n");
	}
	
		printf("Shutdown\n");
		mq_unlink("/csaz8744");
		mq_close(queue);
		free(buffer);
		free(prio);

	return EXIT_SUCCESS;
}

void handler(int sig){
	printf("\nShutdown\n");
	mq_unlink("/csaz8744");
	exit(EXIT_SUCCESS);
}
