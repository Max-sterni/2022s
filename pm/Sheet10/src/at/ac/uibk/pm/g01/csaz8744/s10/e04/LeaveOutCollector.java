package at.ac.uibk.pm.g01.csaz8744.s10.e04;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class LeaveOutCollector<T, A, R> implements Collector {

    @Override
    public Supplier supplier() {
        return null;
    }

    @Override
    public BiConsumer accumulator() {
        return null;
    }

    @Override
    public BinaryOperator combiner() {
        return null;
    }

    @Override
    public Function finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
