package at.ac.uibk.pm.g01.csaz8744.midterm1.e01;

public class Attacker extends OutfieldPlayer{
    private int attackingAbilities;

    public Attacker(String name, Position position, int attackingAbilities){
        super(name, position);
        this.attackingAbilities = attackingAbilities;
    }

    @Override
    public String toString() {
        return super.toString() + " with " + attackingAbilities + " attacking Abilities";
    }
}
