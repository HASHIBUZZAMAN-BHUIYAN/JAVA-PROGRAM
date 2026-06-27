/**
 * Day 09 — OOP II — Solutions
 */

class ShapeSol {
    protected String color;
    ShapeSol(String color) { this.color = color; }
    String getColor()   { return color; }
    double area()       { return 0; }
    double perimeter()  { return 0; }
    @Override
    public String toString() {
        return color + " shape, area=" + String.format("%.2f", area());
    }
}

class CircleSol extends ShapeSol {
    private double radius;
    CircleSol(String color, double radius) { super(color); this.radius = radius; }
    @Override double area()      { return Math.PI * radius * radius; }
    @Override double perimeter() { return 2 * Math.PI * radius; }
    @Override public String toString() {
        return color + " Circle[r=" + radius + "]";
    }
}

class TriangleSol extends ShapeSol {
    private double base, height;
    TriangleSol(String color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }
    @Override double area() { return 0.5 * base * height; }
}

public class Solutions {
    public static void main(String[] args) {
        ShapeSol[] shapes = { new CircleSol("Red", 5), new TriangleSol("Blue", 6, 8) };
        for (ShapeSol s : shapes) {
            System.out.printf("%s  area=%.2f  perim=%.2f%n", s, s.area(), s.perimeter());
        }
    }
}
