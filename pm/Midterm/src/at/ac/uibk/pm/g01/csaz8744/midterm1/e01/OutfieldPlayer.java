package at.ac.uibk.pm.g01.csaz8744.midterm1.e01;

public class OutfieldPlayer {
    private String name;
    private Position position;

    public OutfieldPlayer(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public String toString() {
        return name + " in " + position + " position";
    }
}
