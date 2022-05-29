#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>


#define LIVING_CELL 0
#define DEAD_CELL 1
#define SHUFFLE_CONSTANT 10
#define NAME_LENGTH 14
#define ASCII_OFFSET 48

//Initialising functions
int ** init_starting_state(int width, int height, double density);
void shuffle(int ** arr, int width, int height);
int ** load_state(int width, int height);

//Game functions
int evolve(int ** src, int ** dst, int width, int height, int steps);
int natural_selection(int ** arr, int x, int y, int width, int height);

//IO functions
void printUsage(const char* programName) {
	printf("usage: %s <width> <height> <density> <steps>\n", programName);
}
void save_state(int ** arr, int width, int height, int index);

int main(int argc, char* argv[]) {
	if(argc != 5) {
		printUsage(argv[0]);
		return EXIT_FAILURE;
	}

	const int width = atoi(argv[1]);
	const int height = atoi(argv[2]);
	const double density = atof(argv[3]);
	const int steps = atoi(argv[4]);
	
	if(density < 0 || density > 1 || width <= 0 || height <= 0 || steps <= 0){
		printf("Invalid Parameters\n");
		return EXIT_FAILURE;
	}
	
	printf("width:   %4d\n", width);
	printf("height:  %4d\n", height);
	printf("density: %4.0f%%\n", density * 100);
	printf("steps:   %4d\n", steps);

	// Seeding the random number generator so we get a different starting field
	// every time.
	srand(time(NULL));

	//Initialisation of the arrays
	int ** source = init_starting_state(width, height, density);
	
	int ** destination = malloc(width * sizeof(int *));
    for(int i = 0; i < width; i++){
        destination[i] = malloc(height * sizeof(int));
    }

	evolve(source, destination, width, height, steps);

	for(int i = 0; i < width; i++){
        free(destination[i]);
		free(source[i]);
	}
	free(source);
	free(destination);

	return EXIT_SUCCESS;
}

//Fischer-Yates Shuffle Algorithm
void shuffle(int ** src, int width, int height){
	int x, y, tmp;

	for(int s = 0; s < SHUFFLE_CONSTANT; s++){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				x = rand() % width;
				y = rand() % height;

				tmp = src[x][y];
				src[x][y] = src[i][j];
				src[i][j] = tmp; 
			}
		}
	}
}

//Initialising a starting state with random distribution
int ** init_starting_state(int width, int height, double density){
	//Initialising storage
	int ** out = malloc(width * sizeof(int *));
    for(int i = 0; i < width; i++){
        out[i] = malloc(height * sizeof(int));
    }

	//Filling with values
	int counter = width * height * density;

	for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
                if(counter != 0){
                    out[i][j] = LIVING_CELL;
                    counter--;
                }
                else{
                    out[i][j] = DEAD_CELL;
                }
			}
		}

	shuffle(out, width, height);
	return out;
}

int ** load_state(int width, int height){
	//Initialising storage
	int ** out = malloc(width * sizeof(int *));
    for(int i = 0; i < width; i++){
        out[i] = malloc(height * sizeof(int));
    }

	FILE * newBitmap = fopen("gol_00000.pbm", "r");
	char * buffer = malloc(128), c;
	fgets(buffer, 128, newBitmap);
	fgets(buffer, 128, newBitmap);
	free(buffer);
	for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
                c = getc(newBitmap);
				if(c == '\n'){
					c = getc(newBitmap);
				}
				out[j][i] = atoi(&c);
			}
		}
	
	return out;
}

int evolve(int ** src, int** dst ,int width, int height, int steps){
	save_state(src, width, height, 0);

	for(int i = 1; i <= steps; i++){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				dst[x][y] = natural_selection(src, x, y, width, height);
			}
		}
		save_state(dst, width, height, i);
		int ** tmp = src;
		src = dst;
		dst = tmp;
	}

	return EXIT_SUCCESS;
}

int natural_selection(int ** arr, int x, int y, int width, int height){
	//get the neighbours
	int neighbours = 0, xoff, yoff;
	for(int i = -1; i <= 1; i++){
		for(int j = -1; j <= 1; j++){
			if(i == 0 && j == 0){
				continue;
			}
			xoff = x + i;
			yoff = y + j;

			if(xoff < 0 || xoff == width || yoff < 0 || yoff == height){
				
				continue;
			}
			else{
				if(arr[xoff][yoff] == LIVING_CELL){
					neighbours++;
				}
			}
		}
	}
	
	if(neighbours == 3){
		return LIVING_CELL;
	}
	else if(neighbours == 2){
		return arr[x][y];
	}
	else{
		return DEAD_CELL;
	}
	//return neighbours;
}

//Safe the array as a pbm
void save_state(int ** arr, int width, int height, int index){

	char * filename = malloc(NAME_LENGTH * sizeof(char));

	sprintf(filename, "gol_%05d.pbm", index);
	FILE * newBitmap = fopen(filename, "wb");
	
	if(newBitmap == NULL){
		exit(EXIT_FAILURE);
	}

	fprintf(newBitmap, "P1\n%d %d\n", width, height);

	for(int y = 0; y < height; y++){
		for(int x = 0; x < width; x++){
			putc(ASCII_OFFSET + arr[x][y], newBitmap);
		}
		putc('\n', newBitmap);
	}

	fclose(newBitmap);	
	free(filename);
}
