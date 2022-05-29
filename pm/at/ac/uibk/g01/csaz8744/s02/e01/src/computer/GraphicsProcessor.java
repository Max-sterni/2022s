package computer;

public class GraphicsProcessor extends Component {

    private String graphicsProcessor_type;

    public GraphicsProcessor(String name, int price, String graphicsProcessor_type) {
        super(name, price);
        this.graphicsProcessor_type = graphicsProcessor_type;
    }

    public void printInfo() {
        System.out.println("Graphics Processor: " + name + " : " + graphicsProcessor_type + " Price: " + price);
    }

}
