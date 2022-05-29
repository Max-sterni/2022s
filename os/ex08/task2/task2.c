#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <string.h>
#include <stdbool.h>

#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>

#define BUFFER_SIZE 4096

int main(int argc, char ** argv){
	
	// Argument Guard
	if(argc != 2){
		fprintf(stderr, "invalid number of arguments\n");
		exit(EXIT_FAILURE);
	}
	
	uint16_t port = (uint16_t) atoi(argv[1]);

	if(port == 0){
		perror("invalid argument");
		exit(EXIT_FAILURE);
	}

	// Initializing socket
	int sockfd; 
	struct sockaddr_in addr;
	memset(&addr, 0, sizeof(struct sockaddr_in));
	addr.sin_family = AF_INET;
	addr.sin_port = htons(port);
	addr.sin_addr.s_addr = htonl(INADDR_ANY);

	// Creating socket
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if(sockfd == 0){
		perror("socket failed");
		exit(EXIT_FAILURE);
	}

	if(bind(sockfd, (struct sockaddr *) &addr, sizeof(addr)) < 0){
		perror("bind failed");
		exit(EXIT_FAILURE);
	}

	// Listening on a socket 
	if(listen(sockfd, 0) != 0){
		perror("listening error");
		exit(EXIT_FAILURE);
	}

	printf("Listening on port %u\n", port);
	
	// Connection loop
	socklen_t socklen = sizeof(addr);
	int connectionfd;
	bool shutdown_b = false;
	bool connection = false;	
	char * buffer = malloc(BUFFER_SIZE);
	char * message = malloc(BUFFER_SIZE);
	char template[7] = "Echo: ";
	
	while(!shutdown_b){
		connectionfd = accept(sockfd, (struct sockaddr *) &addr, &socklen);

		if(connectionfd < 0){
			perror("connection error");
			exit(EXIT_FAILURE);
		}
		
		connection = true;
	
		while(connection){
			if(read(connectionfd, buffer, BUFFER_SIZE) <= 0){
				connection = false;
				printf("Open for new Connection\n");
				break;
			}
			printf("%s", buffer);
			if(strncmp("/shutdown", buffer, 9) == 0){
				shutdown_b = true;
				write(connectionfd, "Shutting down\n", strlen("Shutting down\n"));
				break;
			}
			
			strcpy(message, template);
			strcat(message, buffer);			

			write(connectionfd, message, strlen(message));
			memset(buffer, 0, BUFFER_SIZE);
		}
		while(shutdown){
			if(read(connectionfd, buffer, BUFFER_SIZE) <= 0){
				connection = false;
				break;
			}
		}	
	}

	// Shutdown
	free(buffer);
	free(message);
	

	if(close(connectionfd) != 0){
		perror("connectionfd close");
		exit(EXIT_FAILURE);
	}

	if(close(sockfd) != 0){
		perror("sockfd close");
		exit(EXIT_FAILURE);
	}
	return EXIT_SUCCESS; 
}
