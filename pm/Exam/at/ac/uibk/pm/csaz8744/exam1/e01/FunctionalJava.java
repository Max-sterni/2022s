package at.ac.uibk.pm.csaz8744.exam1.e01;

import java.lang.annotation.ElementType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionalJava {
    public long methodA(List<String> values){
        return values.stream()
                .map(String::length)
                .reduce(0, Integer::sum);
    }

    public <E extends Comparable<? super E>> List<E> methodB(List<E> values, E e){
        return values.stream()
                .filter(element -> e.compareTo(element) > 0)
                .collect(Collectors.toList());
    }

    public <E> Map<E, Long> methodC(List<E> values){
        return values.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.summingLong(e -> 1)));
    }

    public static void main(String[] args) {
        FunctionalJava functionalJava = new FunctionalJava();
        System.out.println(functionalJava.methodA(List.of("Hal", "o", "")));
        System.out.println(functionalJava.methodB(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5));
        System.out.println(functionalJava.methodC(List.of(1, 2, 3, 4, 4, 5, 5, 5)));
    }
}
