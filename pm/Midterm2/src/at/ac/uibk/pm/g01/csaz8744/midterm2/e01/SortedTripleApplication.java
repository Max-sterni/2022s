package at.ac.uibk.pm.g01.csaz8744.midterm2.e01;

import java.util.Comparator;

public class SortedTripleApplication {
    public static void main(String[] args) {
        SortedTriple<String> exampleTriple = new SortedTriple<>("bbb", "aa", "c");
        System.out.println(exampleTriple);
        exampleTriple.reverse();
        System.out.println(exampleTriple);
        exampleTriple.resort(Comparator.comparingInt(String::length));
        System.out.println(exampleTriple);
    }
}
