#include<stdio.h>
#include<stdlib.h>
#include<signal.h>
#include<string.h>

#include<unistd.h>

void handler(int sig){

    char * message = malloc(25);
    strcpy(message, "Signal received ");
    char * signumber = malloc(5);
    sprintf(signumber, "%d\n", sig);
    strcat(message, signumber);
    write(STDOUT_FILENO, message, 25);

}

int main()
{
    struct sigaction catch;
    catch.sa_handler = handler; 
    
    sigaction(SIGINT, &catch, NULL);
    sigaction(SIGUSR1, &catch, NULL);
    sigaction(SIGKILL, &catch, NULL); // Kill handle is not changeable 
    sigaction(SIGTERM, &catch, NULL);

    while(1){
        usleep(10);
    }

    return 0;
}
