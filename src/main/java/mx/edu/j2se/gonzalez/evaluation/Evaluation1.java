package mx.edu.j2se.gonzalez.evaluation;

public class Evaluation1 {
    public static void main(String[] args) {
        try {
            Circle circle = new Circle(-1);
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid value for the radius");
        }
        finally {
            Circle circle1 = new Circle(5);
            Circle circle2 = new Circle(7);
            Circle circle3 = new Circle(9);
            biggestCircle(new Circle[]{circle1, circle2, circle3});
        }
    }

    public static void  biggestCircle(Circle[] array){
        int biggestCircle=0;
        double biggestArea=0;
        for (Circle circle : array) {
            if (circle.getRadius() > biggestCircle) {
                biggestCircle = circle.getRadius();
                biggestArea = circle.getArea();
            }
        }
        System.out.println("The biggest circle has a radius of "+biggestCircle+
                " and an area of "+biggestArea);
    }
}
