package computer;

public abstract class Component {

    protected String name;
    protected int price;

    public Component(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public abstract void printInfo();
}
