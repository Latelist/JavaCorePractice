import shapes.abstractions.Shape;

import java.util.Comparator;
import java.util.List;

public class Operations {

    public static Shape compare(Shape shape1, Shape shape2){
        if (shape1.calculateArea() > shape2.calculateArea()) return shape1;
        return shape2;
    }

    public static Shape findMaxShape(List<Shape> shapeList) {
        return shapeList.stream()
                .max(Comparator.comparingDouble(Shape :: calculateArea))
                .orElse(null);
    }
}