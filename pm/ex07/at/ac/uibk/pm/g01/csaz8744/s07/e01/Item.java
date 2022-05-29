package at.ac.uibk.pm.g01.csaz8744.s07.e01;

public class Item implements Identifiable<Long>{
    private final Long id;
    private final String name;
    private final Double price;

    public Item(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getUniqueIdentifier() {
        return id;
    }

    @Override
    public String toString() {
        return id + ": " + name + " " + price;
    }
}
