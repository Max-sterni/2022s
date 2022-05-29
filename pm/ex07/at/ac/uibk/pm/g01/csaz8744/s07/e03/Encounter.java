package at.ac.uibk.pm.g01.csaz8744.s07.e03;

public class Encounter<Result extends Comparable<Result>>{
    private final Player player1;
    private final Player player2;

    private ResultPair<Result> result;

    public Encounter(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void evaluate(ResultGenerator<Result> generator){
        result = generator.getResults(player1, player2);
        if(result.getWinner() == 0){
            player1.evaluateScore(1);
            player2.evaluateScore(1);
        }
        else if(result.getWinner() < 0){
            player1.evaluateScore(3);
        }
        else {
            player2.evaluateScore(3);
        }
    }

    @Override
    public String toString() {
        return player1 + " vs " + player2+ ": " + result;
    }
}
