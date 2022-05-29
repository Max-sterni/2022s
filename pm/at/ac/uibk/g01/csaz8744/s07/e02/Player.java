package at.ac.uibk.pm.g01.csaz8744.s07.e02;

public class Player{
    private final String name;
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void iterateScore(int amount){
        score += amount;
    }

    @Override
    public String toString() {
        return name + " [" + score + "]";
    }
}
