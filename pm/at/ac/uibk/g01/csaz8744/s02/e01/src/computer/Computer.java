package computer;

public class Computer {
    private String model;
    private int manufacturing_year;

    private Processor processor;
    private HardDisk hardDisk;
    private Ram ram1;
    private Ram ram2;
    private GraphicsProcessor graphicsProcessor;

    public Computer(String model, int manufacturing_year,
            Processor processor, HardDisk hardDisk,
            Ram ram1, Ram ram2,
            GraphicsProcessor graphicsProcessor) {
        this.model = model;
        this.manufacturing_year = manufacturing_year;
        this.processor = processor;
        this.hardDisk = hardDisk;
        this.ram1 = ram1;
        this.ram2 = ram2;
        this.graphicsProcessor = graphicsProcessor;
    }

    public int calculatePrice() {
        int price = 0;

        price += processor.getPrice();
        price += hardDisk.getPrice();
        price += ram1.getPrice();
        price += ram2.getPrice();
        price += graphicsProcessor.getPrice();

        return price;
    }

    public void printInfo() {
        System.out.println("Computer Model: " + model + " Manufacturing year: " + manufacturing_year + " Total Price: "
                + calculatePrice());
        processor.printInfo();
        hardDisk.printInfo();
        ram1.printInfo();
        ram2.printInfo();
        graphicsProcessor.printInfo();
    }

    public void printEstimatedWriteTime(double gigsOfData) {
        System.out.println(gigsOfData + "GB will probably take " + hardDisk.estimateDataTransferDuration(gigsOfData)
                + "s to upload");
    }
}
