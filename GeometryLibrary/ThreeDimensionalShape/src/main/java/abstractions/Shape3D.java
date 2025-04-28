package abstractions;

import geometryInterface3D.Geometry3D;
import shapes.abstractions.Shape;
public abstract class Shape3D extends Shape implements Geometry3D {

    @Override
    public String toString() {
        return super.toString() + ". Объём: " + this.calculateVolume();
    }
}
