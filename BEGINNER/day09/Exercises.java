/**
 * Day 09 — OOP II — Exercises
 * Build a simple shape hierarchy.
 */

// Exercise 1: Create a Shape base class with a color field, constructor, and
// abstract-style (just override it) area() and perimeter() methods.
class Shape {
    protected String color;

    Shape(String color) {
        // TODO: set color
    }

    String getColor() { return ""; /* TODO */ }

    // These will be overridden — provide a default that subclasses override
    double area()      { return 0; /* TODO: return 0 */ }
    double perimeter() { return 0; /* TODO: return 0 */ }

    @Override
    public String toString() {
        return ""; // TODO: return color + " shape, area=" + area()
    }
}

// Exercise 2: Create Circle extends Shape
class Circle extends Shape {
    private double radius;

    Circle(String color, double radius) {
        super(color);
        // TODO: set radius
    }

    @Override
    double area()      { return 0; /* TODO: PI * r^2 */ }
    @Override
    double perimeter() { return 0; /* TODO: 2 * PI * r */ }

    @Override
    public String toString() { return ""; /* TODO */ }
}

// Exercise 3: Create TriangleShape extends Shape (with base and height)
class TriangleShape extends Shape {
    private double base;
    private double height;

    TriangleShape(String color, double base, double height) {
        super(color);
        // TODO
    }

    @Override
    double area() { return 0; /* TODO: 0.5 * base * height */ }

    // perimeter() can stay 0 for this exercise
}

public class Exercises {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle("Red", 5),
            new TriangleShape("Blue", 6, 8)
        };

        for (Shape s : shapes) {
            System.out.printf("%s  area=%.2f  perim=%.2f%n",
                s, s.area(), s.perimeter());
        }
    }
}
