package shapes.implementations;

import shapes.abstractions.Shape;

public class Hexagon extends Shape {
    private double side;
    @Override
    public double calculateArea() {
        return (3 * Math.sqrt(3) * side * side)/2;
    }

    @Override
    public double calculatePerimeter() {
        return side * 6;
    }
}
