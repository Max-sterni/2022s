/**
 * Pool
 * 0 = Size of memory block
 * 1 = Pointer to first free memory block
 * Pointer = 0 means this is the last memory block
 * 
 * Memoryblock:
 * index - 1 = size;
 * index = first element
 */
public class Pool {

    int[] memory;

    public Pool(int n) {
        this.memory = new int[n];

        // First pointer
        memory[0] = 2;

        // First memoryblock
        memory[1] = n - 4;
        memory[2] = -1;
    }

    public int malloc(int size) {

        int malloc_return = memory[0], break_pointer = 0, new_break;

        // Go to the next availabe spot if it doesnt fit
        while (memory[malloc_return - 1] < size) {
            // If it was the last available spot terminate with error
            if (memory[malloc_return] == -1) {
                return -1;
            }
            break_pointer = malloc_return;
            malloc_return = memory[malloc_return];
        }
        if (size == memory[malloc_return - 1] && memory[malloc_return] != -1) {
            new_break = memory[malloc_return];
        } else {
            // Locate new free pointer
            new_break = malloc_return + size + 1;

            // Create new free pointer
            memory[new_break] = memory[malloc_return];
            memory[new_break - 1] = memory[malloc_return - 1] - (size + 1);
            memory[memory[break_pointer]] = memory[malloc_return];

        }
        memory[break_pointer] = new_break;

        // Size of new memory_block
        memory[malloc_return - 1] = size;
        return malloc_return;
    }

    public void free(int mem_block_start) {
        int pointer = 0, size = memory[mem_block_start - 1], quicksafe = 0;

        // Goto the relevant pointer
        while (memory[pointer] < mem_block_start + size + 1) {
            quicksafe = pointer;
            pointer = memory[pointer];
        }

        // If there is free space before
        if (pointer != 0 && pointer + memory[pointer - 1] + 1 == mem_block_start) {
            memory[pointer - 1] += size + 1;
            size = memory[pointer - 1];
            mem_block_start = pointer;
            pointer = quicksafe;
        }

        // If there is free space after
        if (memory[pointer] == mem_block_start + size + 1) {
            // Steal the values
            memory[mem_block_start - 1] += memory[mem_block_start + size] + 1;
            memory[mem_block_start] = memory[mem_block_start + size + 1];

            // Change the pointer to mem_block_start
            memory[pointer] = mem_block_start;

        } else {
            memory[mem_block_start] = memory[pointer];
            memory[pointer] = mem_block_start;
        }

        // if (memory[pointer] == mem_block + memory[mem_block] + 1) {
        // // put the next pointer on the new free memory head
        // memory[mem_block] = memory[memory[pointer]];

        // // Update the size
        // memory[mem_block - 1] += memory[memory[pointer - 1]] + 1;

        // // switch the old pointer with the new pointer
        // memory[pointer] = mem_block;
        // } else {
        // memory[mem_block] = memory[pointer];
        // memory[pointer] = mem_block;
        // }
    }

    public void print() {
        int tmp = 0;
        for (int i = 0; i < memory.length; i++) {
            if (i == memory[tmp]) {
                System.out.print("P");
                tmp = memory[tmp];
            }
            System.out.println(i + ": " + memory[i]);
        }
    }

    public void put(int index, int postion, int val) {
        if (index < memory[postion - 1]) {
            memory[index + postion] = val;
        }
    }

}