#include <bits/pthreadtypes.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#include <pthread.h>
#include <semaphore.h>
#include "myqueue.h"

void *guest_function(void *ptr);
void *cook_function(void *ptr);

struct guest_args_t {
  int id;
  sem_t * orders;
  pthread_mutex_t * counter;
  myqueue * queue;
};

struct cook_args_t {
  int id;
  sem_t * orders;
  pthread_mutex_t * counter;
  myqueue * queue;
};

int main(int argc, char **argv) {
  if (argc != 4) {
    fprintf(stderr,
            "<enable notifications> <number of guests> <number of cooks>");
    return EXIT_FAILURE;
  }

  bool notifications;
  int guests_amount, cooks_amount;

  notifications = atoi(argv[1]);
  guests_amount = atoi(argv[2]);
  cooks_amount = atoi(argv[3]);

  if (guests_amount == 0 || cooks_amount == 0 ||
      (notifications == 0 && argv[1][0] != '0')) {
    fprintf(stderr, "invalid input");
    return EXIT_FAILURE;
  }

  pthread_mutex_t * counter = malloc(sizeof(pthread_mutex_t));
  sem_t * orders = malloc(sizeof(sem_t));
  myqueue * order_q = malloc(sizeof(myqueue));

  pthread_mutex_init(counter, NULL);
  sem_init(orders, 0, 0);
  myqueue_init(order_q);

  pthread_t guests[guests_amount];
  pthread_t cooks[cooks_amount];
  struct guest_args_t *guest_args;
  struct cook_args_t *cook_args;

  // Create guests
  for (int i = 0; i < guests_amount; i++) {
    // Create args
    guest_args = malloc(sizeof(struct guest_args_t));
    guest_args->id = i + 1;
    guest_args->counter = counter;
    guest_args->orders = orders;

    // Create thread
    if (pthread_create(guests + i, NULL, guest_function, guest_args)) {
      perror("thread creation error");
      return EXIT_FAILURE;
    }
  }

  // Create cooks
  for (int i = 0; i < cooks_amount; i++) {
    // Create args
    cook_args = malloc(sizeof(struct cook_args_t));
    cook_args->id = i + 1;
    cook_args->counter = counter;
    cook_args->orders = orders;

    // Create thread
    if (pthread_create(cooks + i, NULL, cook_function, cook_args)) {
      perror("thread creation error");
      return EXIT_FAILURE;
    }
  }

  return EXIT_SUCCESS;
}

void *guest_function(void *ptr) {
  // Convenience
  struct guest_args_t *args = (struct guest_args_t *)ptr;

  // Place order
 sem_post(args->orders);
  return NULL;
}

void *cook_function(void *ptr) {
  // Convenience
  struct guest_args_t *args = (struct guest_args_t *)ptr;

  printf("cook%d\n", args->id);
  return NULL;
}