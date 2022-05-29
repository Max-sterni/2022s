package at.ac.uibk.pm.g01.csaz8744.s07.e03;

public class Player{
    private final String name;
    private int score;
    private int encounters;
    private int wins;
    private int draws;
    private int losses;

    public Player(String name) {
        this.name = name;
        this.encounters = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void evaluateScore(int amount){
        score += amount;
        switch (amount) {
            case 3: wins++; break;
            case 1: draws++; break;
            case 0: losses++; break;
        }
        encounters++;
    }

    @Override
    public String toString() {
        return name;
    }

    public String nameWithScore(){
        return name + " [" + score + "] \n\t Encounters: " + encounters + "\n\t Wins: " + wins + "\n\t Losses: " + losses+ "\n\t Draws: " + draws;
    }

}
