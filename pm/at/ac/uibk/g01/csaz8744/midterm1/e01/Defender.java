package at.ac.uibk.pm.g01.csaz8744.midterm1.e01;

public class Defender extends OutfieldPlayer{
    private int defensiveAbilities;

    public Defender(String name, Position position, int defensiveAbilities){
        super(name, position);
        this.defensiveAbilities = defensiveAbilities;
    }

    @Override
    public String toString() {
        return super.toString() + " with " + defensiveAbilities + " defensive Abilities";
    }
}
