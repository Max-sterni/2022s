#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include <sys/mman.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <semaphore.h>
	

u_int64_t * shm_init(u_int64_t b);

int main(int argc, char * argv[]){
	
	//Guards for arguments
	if(argc != 3){
		fprintf(stderr, "task1 <numbers n> <buffer b>\n");
		return EXIT_FAILURE;
	}
	
	u_int64_t n = atoi(argv[1]), b = atoi(argv[2]);

	if(n == 0 || b == 0){
		fprintf(stderr, "invalid number\n");
		return EXIT_FAILURE;
	}	


	//Initializing shared memory and forking
	u_int64_t * buffet = shm_init(b), consumer, producer;

	producer = fork();

	if(producer == 0) {
		
		//Link the semaphore		
		sem_t * consumable = sem_open("/consumable", O_CREAT, S_IWUSR | S_IRUSR, 0);
		sem_t * free = sem_open("/free", O_CREAT, S_IWUSR | S_IRUSR, b);

		for (u_int64_t i = 0; i < n; i++){
			sem_wait(free);

			buffet[i % b + 1] = i + 1;

			sem_post(consumable);
		}
		exit(EXIT_SUCCESS);
	}

	consumer = fork();

	if(consumer == 0) {
		sem_t * consumable = sem_open("/consumable", O_CREAT, S_IWUSR | S_IRUSR, 0);
		sem_t * free = sem_open("/free", O_CREAT, S_IWUSR | S_IRUSR, b);
		for (u_int64_t i = 0; i < n; i++){
			sem_wait(consumable);

			buffet[0] += buffet[i % b + 1];

			sem_post(free);
		}

		sem_unlink("/consumable");
		sem_unlink("/free");
		exit(EXIT_SUCCESS);
	}
	
	waitpid(producer, NULL, 0);
	waitpid(consumer, NULL, 0);

	printf("%lu\n", buffet[0]);
	munmap(buffet, sizeof(u_int64_t) * b);

	return EXIT_SUCCESS;
}

u_int64_t * shm_init(u_int64_t b){
	//setting up the shared memory segment
	int shm_fd = shm_open("buffer", O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
	
	ftruncate(shm_fd, sizeof(u_int64_t) * (b + 1));

	u_int64_t * shm_pointer = mmap(NULL, sizeof(u_int64_t) * (b + 1) , PROT_WRITE | PROT_READ, 
			MAP_SHARED, shm_fd, 0);
	shm_unlink("buffer");
	
	return shm_pointer;

}
