package shapes.abstractions;

import geometryInterface.Geometry;

public abstract class Shape implements Geometry {
    @Override
    public String toString() {
        return "Фигура — " + this.getClass()
                + ". Площадь: " + calculateArea()
                + ". Периметр — " + calculatePerimeter();
    }
}
