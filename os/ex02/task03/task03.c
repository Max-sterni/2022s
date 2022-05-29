#include<stdlib.h>
#include<stdio.h>
#include<string.h>

int main(int argc, char const *argv[])
{
    if(argc == 2){
        int number = atoi(argv[1]);
        int length = 1, i = 10;
        while(length % i != length){
            length++;
            i *= 10;
        }

        if(length != strlen(argv[1])){
            return 3;
        }

        if(number % 2 == 0){
            return 0;
        }
        else{
            return 1;
        }
        
    }
    return 4;
}
