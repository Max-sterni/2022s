package at.ac.uibk.pm.g01.csaz8744.s03.e05;

/**
 * Triangle
 */
public class Triangle extends Shape{
   
    private double sideA;
    private double sideB;
    private double sideC;


    public Triangle(double sideA, double sideB, double sideC, Color bg_color) {
        super(bg_color);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }


    @Override
    public double getArea() {
        double s = (sideA+ sideB + sideC)/2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }
    @Override
    public double getCircumference() {
        return sideA + sideB + sideC;
    }
    
}