package at.ac.uibk.pm.g01.csaz8744.s03.e05;
/**
 * Shape
 */
public abstract class Shape {

    private Color bg_color;

    public Shape(Color bg_color) {
        this.bg_color = bg_color;
    }


    public Color getBg_color() {
        return this.bg_color;
    }

    public void setBg_color(Color bg_color) {
        this.bg_color = bg_color;
    }
    
    abstract public double getArea();

    abstract public double getCircumference();

}