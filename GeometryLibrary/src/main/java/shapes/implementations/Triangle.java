package shapes.implementations;

import shapes.abstractions.Shape;

public class Triangle extends Shape {

    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {


        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }
    @Override
    public double calculateArea() {
        double p = (sideA + sideB + sideC) / 2.0;
        return Math.sqrt(p * (p-sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

}
