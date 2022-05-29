import javax.sound.sampled.spi.AudioFileReader;

public class Main {
    public static void main(String[] args) {
        Pool pool = new Pool(200);
        int a = pool.malloc(50);
        int b = pool.malloc(50);
        pool.free(a);
        pool.free(b);
        pool.print();
    }
}
