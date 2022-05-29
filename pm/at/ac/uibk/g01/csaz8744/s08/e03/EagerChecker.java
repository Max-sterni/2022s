package at.ac.uibk.pm.g01.csaz8744.s08.e03;

public class EagerChecker {

    static String eagerChecker(boolean first, boolean second) {
        return first && second ? "match" : "incompatible!";
    }

}
