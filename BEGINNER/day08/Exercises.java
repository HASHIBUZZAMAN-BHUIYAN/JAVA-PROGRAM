/**
 * Day 08 — OOP I — Exercises
 * Complete each class below by filling in the TODOs.
 */

// Exercise 1: Complete the Rectangle class
class Rectangle {
    private double width;
    private double height;

    // TODO: Add a constructor that takes width and height
    Rectangle(double width, double height) {
        // TODO
    }

    // TODO: Add getters for width and height
    double getWidth()  { return 0; /* TODO */ }
    double getHeight() { return 0; /* TODO */ }

    // TODO: Add area() method returning width * height
    double area() { return 0; /* TODO */ }

    // TODO: Add perimeter() method returning 2*(width+height)
    double perimeter() { return 0; /* TODO */ }

    // TODO: Add toString() like "Rectangle[3.0 x 4.0]"
    @Override
    public String toString() { return ""; /* TODO */ }
}

// Exercise 2: Complete the Temperature class
class Temperature {
    private double celsius;

    // TODO: Constructor taking celsius
    Temperature(double celsius) { /* TODO */ }

    double getCelsius()    { return 0; /* TODO */ }
    // TODO: toFahrenheit() — formula: C * 9/5 + 32
    double toFahrenheit()  { return 0; /* TODO */ }
    // TODO: toKelvin()     — formula: C + 273.15
    double toKelvin()      { return 0; /* TODO */ }

    @Override
    public String toString() { return ""; /* TODO */ }
}

public class Exercises {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(3.0, 4.0);
        System.out.println(r);                      // Rectangle[3.0 x 4.0]
        System.out.println("Area: " + r.area());    // 12.0
        System.out.println("Perimeter: " + r.perimeter()); // 14.0

        Temperature t = new Temperature(100.0);
        System.out.println(t);
        System.out.println("Fahrenheit: " + t.toFahrenheit()); // 212.0
        System.out.println("Kelvin: " + t.toKelvin());         // 373.15
    }
}
