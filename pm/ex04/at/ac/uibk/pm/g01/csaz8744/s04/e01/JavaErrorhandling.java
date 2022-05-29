package at.ac.uibk.pm.g01.csaz8744.s04.e01;

public class JavaErrorhandling {
    public static int sumPositiveInts(int... ints) throws IllegalArgumentException, ArithmeticException {
        int sum = 0;
        for (int anInt : ints) {
            if (anInt < 0) {
                throw new IllegalArgumentException("only positive values are allowed!");
            }
            sum += anInt;
            if (sum < 0) {
                throw new ArithmeticException("overflow!");
            }
        }
        return sum;
    }

}
