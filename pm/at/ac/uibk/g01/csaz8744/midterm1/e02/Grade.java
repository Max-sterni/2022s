package at.ac.uibk.pm.g01.csaz8744.midterm1.e02;

public enum Grade {
    SGT1(90),
    GUT2(80),
    BEF3(70),
    GEN4(60),
    NGD5(0);

    private final int lowerBound;

    Grade(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public static Grade from(int points) {
        if (points < 0 || points > 100) {
            throw new IllegalArgumentException("Invalid points");
        }
        if (points < GEN4.lowerBound) {
            return NGD5;
        }
        if (points < BEF3.lowerBound) {
            return GEN4;
        }
        if (points < GUT2.lowerBound) {
            return BEF3;
        }
        if (points < SGT1.lowerBound) {
            return GUT2;
        }
        return SGT1;
    }
}
