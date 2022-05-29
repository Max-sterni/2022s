package at.ac.uibk.pm.g01.csaz8744.s08.e03;

public class LazyChecker {
    static String lazyChecker(int n, int m) {
        return LazyMain.higherThan100(n) && LazyMain.higherThan100(m) ? "match" : "incompatible!";
    }
}
