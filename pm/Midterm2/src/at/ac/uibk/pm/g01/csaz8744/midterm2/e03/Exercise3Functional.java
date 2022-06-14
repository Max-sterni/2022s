package at.ac.uibk.pm.g01.csaz8744.midterm2.e03;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercise3Functional implements Exercise3Interface {

    @Override
    public List<Integer> methodA(List<Integer> list) {
        return list.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public int methodB(List<List<Integer>> lists) {
        return lists.stream()
                .map(list -> list.size())
                .collect(Collectors.summingInt(Integer::intValue));
    }

    @Override
    public Optional<String> methodC(List<String> list) {
        return list.stream()
                .min((String x, String y)-> -Integer.compare(x.length(), y.length()));
    }
}
