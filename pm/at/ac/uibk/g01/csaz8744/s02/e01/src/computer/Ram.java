package computer;

public class Ram extends Component {

    private String model;
    private int memory;
    private double clock_rate;

    public Ram(String name, int price, String model, int memory, double clock_rate) {
        super(name, price);
        this.model = model;
        this.memory = memory;
        this.clock_rate = clock_rate;
    }

    public void printInfo() {
        System.out.println("RAM-Stick: " + name + " : " + model + " Price: " + price + " Memory: " + memory
                + " Clock Rate: " + clock_rate);
    }

}
