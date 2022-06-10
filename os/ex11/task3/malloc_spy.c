#einclude <stdlib.h>
#include <stdio.h>
#include <unistd.h>

#define __USE_GNU
#include <dlfcn.h>
#include <string.h>

void print_number(size_t number);

void *malloc(size_t size)
{
    void *(*loaded_malloc)(size_t) = dlsym(RTLD_NEXT, "malloc");

    write(STDOUT_FILENO, "allocating ", strlen("allocating "));
    print_number(size);
    write(STDOUT_FILENO, " bytes\n", strlen(" bytes\n"));

    return loaded_malloc(size);
}

void print_number(size_t number)
{
    if (number > 9)
    {
        print_number(number / 10);
    }
    const char digit = '0' + number % 10;
    write(STDOUT_FILENO, &digit, 1);
}
