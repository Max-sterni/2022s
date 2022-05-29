package at.ac.uibk.pm.g01.csaz8744.s03.e05;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        Circle circle = new Circle(5, Color.RED);
        Rectangle rectangle = new Rectangle(12 , 3, Color.BLUE);
        Triangle triangle = new Triangle(5, 12, 13, Color.GREEN);

        System.out.println("Areas: " + circle.getArea() + " " + rectangle.getArea() + " " + triangle.getArea());
        System.out.println("Circumferences: " + circle.getCircumference() + " " + rectangle.getCircumference() + " " + triangle.getCircumference());
    }
}