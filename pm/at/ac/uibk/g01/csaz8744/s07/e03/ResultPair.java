package at.ac.uibk.pm.g01.csaz8744.s07.e03;

public class ResultPair<Result extends Comparable<Result>>{
    private final Result player1Result;
    private final Result player2Result;

    public ResultPair(Result player1Result, Result player2Result) {
        this.player1Result = player1Result;
        this.player2Result = player2Result;
    }

    public int getWinner(){
        return -player1Result.compareTo(player2Result);
    }

    @Override
    public String toString() {
        return player1Result + " | " + player2Result;
    }
}
