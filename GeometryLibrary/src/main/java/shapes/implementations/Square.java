package shapes.implementations;

public class Square extends Rectangle {

    public Square(double sideA, double sideB) {
        super(sideA, sideB);
    }

    public Square(double side){
        super();
        this.sideA = side;
        this.sideB = side;
    }
}
