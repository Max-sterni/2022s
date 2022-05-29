#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/stat.h>
#include <fcntl.h>
#include <mqueue.h>
#include <string.h>

#define MAX_MSG_SIZE 4096

int main(int argc, char *argv[])
{
	// Argument guard
	
	if(argc != 3){
		fprintf(stderr, "Invalid arguments\n");
		exit(EXIT_FAILURE);
	}
	
	if(strlen(argv[1]) < 2|| argv[1][0] != '/'){
		fprintf(stderr, "Invalid printer\n");
		exit(EXIT_FAILURE);
	}

	int prio = atoi(argv[2]);
	
	if(prio == 0 && argv[2][0] != '0'){
		fprintf(stderr, "Invalid priority\n");
		exit(EXIT_FAILURE);
	}

	// Setup the q
	mqd_t queue = mq_open(argv[1] , O_WRONLY);

	if(queue < 0){
		fprintf(stderr, "Unknown printer\n");
		exit(EXIT_FAILURE);
	}

	char buffer[MAX_MSG_SIZE];
	ssize_t size;
	
	size = read(STDIN_FILENO, buffer, MAX_MSG_SIZE);	

	mq_send(queue, buffer, size, prio);	

	mq_close(queue);

	return EXIT_SUCCESS;
}
