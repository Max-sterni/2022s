package at.ac.uibk.pm.g01.csaz8744.s07.e03;

import java.util.Random;
import java.util.function.Function;

public class RandomResultFunction implements Function<Random, Integer> {


    private final int maxPoints = 10;
    @Override
    public Integer apply(Random random) {
        return random.nextInt(maxPoints);
    }
}
