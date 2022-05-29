package at.ac.uibk.pm.g01.csaz8744.s07.e03;

public interface ResultGenerator<Result extends Comparable<Result>> {
    ResultPair<Result> getResults(Player player1, Player player2);
}
