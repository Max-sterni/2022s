package at.ac.uibk.pm.g01.csaz8744.midterm2.e03;

import java.util.List;

public class Exercise3Application {

    public static void main(String[] args) {
        Exercise3Interface imperative = new Exercise3Imperative();
        Exercise3Interface functional = new Exercise3Functional();

        List<Integer> listA = List.of(25, 13, -11, 123, -4, 25, 3, 7, 25);
        List<List<Integer>> listB =
                List.of(listA.subList(0, 5), listA.subList(2, 4), listA.subList(3, 8));
        List<String> listC = List.of("Berlin", "London", "Rom", "Paris", "Washington", "Oslo");

        System.out.printf("methodA%n   Imperative: %s%n   Functional: %s%n%n",
                imperative.methodA(listA),
                functional.methodA(listA));

        System.out.printf("methodB%n   Imperative: %s%n   Functional: %s%n%n",
                imperative.methodB(listB),
                functional.methodB(listB));

        System.out.printf("methodC%n   Imperative: %s%n   Functional: %s%n%n",
                imperative.methodC(listC).orElse("<Empty List>"),
                functional.methodC(listC).orElse("<Empty List>"));
    }

}
