package org.example;

public class Triangle {
    private int a;
    private int b;
    private int c;


    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void isTriangle() throws NotTriangleException {
        if ((a + b) <= c | (a + c) <= b | (b + c) <= a) {
            throw new NotTriangleException("Данная фигура не треугольник. Введите корректные значения.");
        }
    }

    public void dataFormat () throws DataFormatException{
        if (a <= 0 | b <= 0 | c <=0) {
            throw new DataFormatException("Введены некорректные значения. Введите целое положительное число.");
        }
    }

    public Double getArea() {
        try {
            dataFormat();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
        try {
            isTriangle();
        } catch (NotTriangleException e) {
            e.printStackTrace();
        } finally {
            double p = (a + b + c) / 2.0;
            double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            return s;
        }
    }

    @Override
    public String toString() {
        return "Площадь треугольника со сторонами a = " + a + " b = " + b + " c = " + c + " равна " + getArea();
    }
}
