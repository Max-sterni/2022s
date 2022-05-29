import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueBenchmark {
    public static final int MAX_SIZE = 33_554_432; //2^25, reduce size if RAM constrained
    public static final int ITERATIONS = 5;

    public static void main(String[] args) {
        Queue<Integer> linkedList = new LinkedList<>(); //double-linked list
        Queue<Integer> cyclicArray = new ArrayDeque<>(); //cyclic array with starting size 16
        Queue<Integer> cyclicArrayWithSize = new ArrayDeque<>(MAX_SIZE); //cyclic array with starting size CYCLIC_ARRAY_SIZE

        System.out.println("linkedList:");
        addAndRemoveBenchmark(linkedList);
        System.out.println("cyclicArray:");
        addAndRemoveBenchmark(cyclicArray);
        System.out.println("cyclicArray with pre-allocated memory:");
        addAndRemoveBenchmark(cyclicArrayWithSize);
    }

    private static void addAndRemoveBenchmark(Queue<Integer> queue) {
        for (int size = 65536; size <= MAX_SIZE; size *= 2) {
            //do multiple iterations and take average
            int total_runtime = 0;
            for (int k = 0; k < ITERATIONS; k++) {
                long timer1 = System.currentTimeMillis();
                for (int j = 0; j < size; j++) {
                    queue.add(j);
                }
                for (int j = 0; j < size; j++) {
                    queue.remove();
                }
                long timer2 = System.currentTimeMillis();
                total_runtime += (timer2 - timer1);
            }
            System.out.println("Size: " + size + ", average elapsed time in ms: " + total_runtime / ITERATIONS);
        }
    }
}
