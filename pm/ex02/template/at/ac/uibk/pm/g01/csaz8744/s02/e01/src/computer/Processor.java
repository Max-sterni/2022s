package computer;

public class Processor extends Component {

    private String processor_type;

    public Processor(String name, int price, String processor_type) {
        super(name, price);
        this.processor_type = processor_type;
    }

    public void printInfo() {
        System.out.println("Processor: " + name + " : " + processor_type + " Price: " + price);
    }

}
