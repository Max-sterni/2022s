package at.ac.uibk.pm.g01.csaz8744.s07.e03;

import java.util.Random;
import java.util.function.Function;

public class RandomResultGenerator<Result extends Comparable<Result>> implements ResultGenerator {
    private final Function<Random, Result> creator;
    private Random random;

    public RandomResultGenerator(Function<Random, Result> creator) {
        this.creator = creator;
        this.random = new Random();
    }

    @Override
    public ResultPair<Result> getResults(Player player1, Player player2) {
        return new ResultPair<>(creator.apply(random), creator.apply(random));
    }
}
