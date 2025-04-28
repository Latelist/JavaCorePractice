package shapes.abstractions;

import geometryInterface.Geometry2D;

public abstract class Shape implements Geometry2D {
    @Override
    public String toString() {
        return "Фигура — " + this.getClass()
                + ". Площадь: " + calculateArea()
                + ". Периметр — " + calculatePerimeter();
    }
}
