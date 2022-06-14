#include <bits/pthreadtypes.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#include <pthread.h>
#include <semaphore.h>
#include "myqueue.h"
#include <stdatomic.h>

void *guest_function(void *ptr);
void *cook_function(void *ptr);

struct guest_args_t {
  int id;
};

struct cook_args_t {
  int id;
};

struct order_t{
  int id;
};

pthread_cond_t new_order;
sem_t orders;
pthread_mutex_t counter;
myqueue order_q;

int orders_amount;

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


  pthread_mutex_init(&counter, NULL);
  sem_init(&orders, 0, 0);
  myqueue_init(&order_q);

  pthread_t guests[guests_amount];
  pthread_t cooks[cooks_amount];
  struct guest_args_t *guest_args;
  struct cook_args_t *cook_args;

  // Create guests
  for (int i = 0; i < guests_amount; i++) {
    // Create args
    guest_args = malloc(sizeof(struct guest_args_t));
    guest_args->id = i;
    
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
    cook_args->id = i;

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
  struct order_t * order = malloc(sizeof(struct order_t));
  write(0,"T\n", 2);

  // Place order
  // pthread_mutex_lock(&counter);

  // order->id = orders_amount;

  // myqueue_push(&order_q, order);
  // printf("Guest %d has made a meal order %d\n", args->id, order->id);

  // orders_amount++;
  
  // pthread_mutex_unlock(&counter);

  // sem_post(&orders);
  
  free(order);
  free(args);
  return NULL;
}

void *cook_function(void *ptr) {
  // Convenience
  struct guest_args_t *args = (struct guest_args_t *)ptr;

  printf("Cook %d created", args->id);

  // sem_wait(&orders);
  // // Receive order
  // pthread_mutex_lock(&counter);

  // struct order_t * order =  myqueue_pop(&order_q);

  // printf("Cook %d has received a meal order %d\n", args->id, order->id);
  
  // pthread_mutex_unlock(&counter);

  free(args);
  return NULL;
}
