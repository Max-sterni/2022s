#include<stdio.h>
#include<stdlib.h>


int main()
{
    char * my_env_var_val;
    my_env_var_val = getenv("MY_ENV_VAR");

    if(my_env_var_val == NULL){
        printf("MY_ENV_VAR is not set\n");
    }    
    else{
        printf("MY_ENV_VAR is set to %s\n", my_env_var_val);
    }
    return 0;
}
