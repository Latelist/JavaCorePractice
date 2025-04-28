package implementations;

import abstractions.Shape3D;

public class Cube extends Shape3D {

    private double side;

    public Cube(double side) {
        this.side = side;
    }
    @Override
    public double calculateArea() {
        return 6 * side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 12 * side;
    }

    @Override
    public double calculateVolume() {
        return side * side * side;
    }
}
