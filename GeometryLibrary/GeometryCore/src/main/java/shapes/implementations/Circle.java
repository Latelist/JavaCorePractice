package shapes.implementations;


import shapes.abstractions.Shape;

public class Circle extends Shape {

    private double raduis;

    public Circle(double raduis) {
        this.raduis = raduis;
    }
    @Override
    public double calculateArea() {
        return Math.PI * raduis * raduis;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * raduis;
    }
}
