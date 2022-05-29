package computer;
import computer.HardDisk.disk_type_t;

public class Main {

    public static void main(String[] args) {

        Ram ram1 = new Ram("HyperJ RGB Ultra", 80, "DDR5", 16, 4500.0);
        Ram ram2 = new Ram("Holo polo molo", 3000, "DDR1234", 1096, 5.3);

        Processor processor1 = new Processor("Intel irgendwas", 280, "i5-k");
        Processor processor2 = new Processor("AMD viel zu krass", 634561, "super good overclock");

        HardDisk hardDisk1 = new HardDisk("Lame gigibyte", 800, disk_type_t.HDD, 1000, 0.5);
        HardDisk hardDisk2 = new HardDisk("Sick gogobyte", 12343345, disk_type_t.SSD, 99999999, 30.0);

        GraphicsProcessor graphicsProcessor1 = new GraphicsProcessor("Some generic brand", 100000,
                "super impressive model");
        GraphicsProcessor graphicsProcessor2 = new GraphicsProcessor("Nvidia", 9999888, "RTX 5090TiXtFl");

        Computer c1 = new Computer("normal", 2019, processor1, hardDisk1, ram1, ram1, graphicsProcessor1);
        Computer c2 = new Computer("extreme", 2222, processor2, hardDisk2, ram2, ram2, graphicsProcessor2);

        c1.printInfo();
        System.out.println();
        c1.printEstimatedWriteTime(1000);
        System.out.println();

        c2.printInfo();
        System.out.println();
        c2.printEstimatedWriteTime(1000);
    }

}
