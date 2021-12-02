package mx.edu.j2se.gonzalez.evaluation;

public class Circle {
    private int radius = 1;

    Circle (int radius) throws IllegalArgumentException{
        if (radius<=0) throw new IllegalArgumentException();
        this.radius=radius;
    }
    public void setRadius(int radius) throws IllegalArgumentException{
        if(radius<=0)throw new IllegalArgumentException();
        this.radius=radius;
    }
    public int getRadius(){
        return radius;
    }
    public double getArea(){
        return Math.PI*radius*radius;
    }
}
