/**
 * Day 08 — OOP I — Solutions
 */

class RectangleSol {
    private double width;
    private double height;

    RectangleSol(double width, double height) {
        this.width  = width;
        this.height = height;
    }

    double getWidth()    { return width; }
    double getHeight()   { return height; }
    double area()        { return width * height; }
    double perimeter()   { return 2 * (width + height); }

    @Override
    public String toString() {
        return "Rectangle[" + width + " x " + height + "]";
    }
}

class TemperatureSol {
    private double celsius;

    TemperatureSol(double celsius) { this.celsius = celsius; }

    double getCelsius()   { return celsius; }
    double toFahrenheit() { return celsius * 9.0 / 5.0 + 32; }
    double toKelvin()     { return celsius + 273.15; }

    @Override
    public String toString() {
        return String.format("Temperature[%.1f°C / %.1f°F / %.2fK]",
               celsius, toFahrenheit(), toKelvin());
    }
}

public class Solutions {
    public static void main(String[] args) {
        RectangleSol r = new RectangleSol(3.0, 4.0);
        System.out.println(r);
        System.out.println("Area: " + r.area());
        System.out.println("Perimeter: " + r.perimeter());

        TemperatureSol t = new TemperatureSol(100.0);
        System.out.println(t);
        System.out.println("Fahrenheit: " + t.toFahrenheit());
        System.out.println("Kelvin: " + t.toKelvin());
    }
}
