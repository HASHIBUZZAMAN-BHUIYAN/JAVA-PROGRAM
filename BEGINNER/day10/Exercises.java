/**
 * Day 10 — OOP III — Exercises
 */

// Exercise 1: Create abstract class Animal2 with abstract method speak().
// Subclasses: Dog2, Cat2, Bird2 each implementing speak().
abstract class Animal2 {
    protected String name;
    Animal2(String name) { this.name = name; }
    abstract String speak(); // TODO: implement in subclasses
    void introduce() { System.out.println(name + " says: " + speak()); }
}

class Dog2 extends Animal2 {
    Dog2(String name) { super(name); }
    @Override String speak() { return ""; /* TODO: "Woof!" */ }
}

class Cat2 extends Animal2 {
    Cat2(String name) { super(name); }
    @Override String speak() { return ""; /* TODO: "Meow!" */ }
}

// Exercise 2: Create interface Resizable with resize(double factor).
// Apply it to a Box class that has width and height.
interface Resizable {
    void resize(double factor); // TODO: multiply width and height by factor
}

class Box implements Resizable {
    double width, height;
    Box(double w, double h) { this.width = w; this.height = h; }

    @Override
    public void resize(double factor) {
        // TODO
    }

    @Override
    public String toString() { return "Box[" + width + " x " + height + "]"; }
}

public class Exercises {
    public static void main(String[] args) {
        Animal2[] animals = { new Dog2("Rex"), new Cat2("Luna") };
        for (Animal2 a : animals) a.introduce();

        Box box = new Box(4.0, 5.0);
        System.out.println("Before: " + box);
        box.resize(2.0);
        System.out.println("After 2x: " + box); // Box[8.0 x 10.0]
    }
}
