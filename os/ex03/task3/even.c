#include<stdlib.h>
#include<stdio.h>
#include<string.h>

int main(int argc, char const *argv[])
{
    if(argc == 2){
        int number = atoi(argv[1]);

        if(number == 0 && argv[1][0] != '0'){
            return 3;
        }

        if(number % 2 == 0){
            return 0;
        }
        else{
            return 1;
        }
        
    }
    return 2;
}
