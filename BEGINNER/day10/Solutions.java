/**
 * Day 10 — OOP III — Solutions
 */

abstract class Animal2Sol {
    protected String name;
    Animal2Sol(String name) { this.name = name; }
    abstract String speak();
    void introduce() { System.out.println(name + " says: " + speak()); }
}
class Dog2Sol extends Animal2Sol {
    Dog2Sol(String name) { super(name); }
    @Override String speak() { return "Woof!"; }
}
class Cat2Sol extends Animal2Sol {
    Cat2Sol(String name) { super(name); }
    @Override String speak() { return "Meow!"; }
}

interface ResizableSol {
    void resize(double factor);
}

class BoxSol implements ResizableSol {
    double width, height;
    BoxSol(double w, double h) { this.width = w; this.height = h; }
    @Override public void resize(double factor) { width *= factor; height *= factor; }
    @Override public String toString() { return "Box[" + width + " x " + height + "]"; }
}

public class Solutions {
    public static void main(String[] args) {
        Animal2Sol[] animals = { new Dog2Sol("Rex"), new Cat2Sol("Luna") };
        for (Animal2Sol a : animals) a.introduce();

        BoxSol box = new BoxSol(4.0, 5.0);
        System.out.println("Before: " + box);
        box.resize(2.0);
        System.out.println("After 2x: " + box);
    }
}
