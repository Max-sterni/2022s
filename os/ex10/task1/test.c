#include <stdlib.h>
#include <stdio.h>

#include "allocator_tests.h"
#include "membench.h"


int main(void){
	test_free_list_allocator();
	return EXIT_SUCCESS;
}
