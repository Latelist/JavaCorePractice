package implementations;

import abstractions.Shape3D;

public class SquarePyramid extends Shape3D {

    private double baseSide;
    private double height;

    public SquarePyramid(double baseSide, double height) {
        this.baseSide = baseSide;
        this.height = height;
    }
    @Override
    public double calculateArea() {
        double baseArea = baseSide * baseSide;
        double apothem = Math.sqrt(Math.pow(height, 2) + Math.pow(baseSide/2, 2));
        double sideArea = 2 * baseSide * apothem;
        return baseArea + sideArea;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * baseSide + 4 * calculateEdge();
    }

    @Override
    public double calculateVolume() {
        double baseArea = baseSide * baseSide;
        return (baseArea * height) / 3;
    }

    public double calculateEdge() {
        return Math.sqrt(height * height + (baseSide * baseSide)/2);
    }
}
