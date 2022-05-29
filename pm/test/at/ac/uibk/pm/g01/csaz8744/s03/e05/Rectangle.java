package at.ac.uibk.pm.g01.csaz8744.s03.e05;

/**
 * Rectangle
 */
public class Rectangle extends Shape{
    
    private double width;
    private double height;


    public Rectangle(double width, double height, Color bg_color) {
        super(bg_color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getCircumference() {
        return 2* height + 2 * width;
    }
}