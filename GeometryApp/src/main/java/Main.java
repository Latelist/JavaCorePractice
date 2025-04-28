import implementations.Cube;
import implementations.Sphere;
import shapes.abstractions.Shape;
import shapes.implementations.Rectangle;
import shapes.implementations.Circle;
import shapes.implementations.Triangle;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Фигуры из библиотеки GeometryLibrary
        Circle circle = new Circle(10);
        System.out.println(circle);

        Rectangle rectangle = new Rectangle(1, 8);
        System.out.println(rectangle);

        Triangle triangle = new Triangle(4, 9, 6);
        System.out.println(triangle);

        //Метод сравнения из модуля GeometryUtils
        Shape biggerShape = Operations.compare(circle, rectangle);
        System.out.println(biggerShape);

        List<Shape> shapeList = List.of(circle, rectangle, triangle);
        Shape maxShape = Operations.findMaxShape(shapeList);
        System.out.println(maxShape);

        Cube cube = new Cube(4);
        System.out.println(cube);

        Sphere sphere = new Sphere(5);
        System.out.println(sphere);
    }
}