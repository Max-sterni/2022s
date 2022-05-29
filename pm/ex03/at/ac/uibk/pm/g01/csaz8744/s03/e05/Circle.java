package at.ac.uibk.pm.g01.csaz8744.s03.e05;

import java.net.CookieHandler;

/**
 * Circle
 */
public class Circle extends Shape{

    private double radius;

    public Circle(double radius, Color bg_color) {
        super(bg_color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return 2* Math.PI * radius * radius;
    }

    @Override
    public double getCircumference() {
        return radius * Math.PI;
    }
    
}