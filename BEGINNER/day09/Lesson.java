/**
 * Day 09 — OOP II: Inheritance, super, Overriding, Polymorphism
 */

// ── Parent (superclass / base class) ────────────────────────────────────────
class Animal {
    protected String name;
    protected int    age;

    Animal(String name, int age) {
        this.name = name;
        this.age  = age;
    }

    void eat() {
        System.out.println(name + " is eating.");
    }

    // This method will be overridden by subclasses
    String sound() {
        return "...";
    }

    void describe() {
        System.out.println(name + " (age " + age + ") says: " + sound());
    }

    @Override
    public String toString() {
        return "Animal[name=" + name + ", age=" + age + "]";
    }
}

// ── Child class 1: Dog extends Animal ───────────────────────────────────────
class Dog extends Animal {
    private String breed;

    Dog(String name, int age, String breed) {
        super(name, age);   // MUST call parent constructor first
        this.breed = breed;
    }

    @Override
    String sound() { return "Woof!"; }  // override parent method

    void fetch() {
        System.out.println(name + " fetches the ball!");
    }

    @Override
    public String toString() {
        return "Dog[name=" + name + ", age=" + age + ", breed=" + breed + "]";
    }
}

// ── Child class 2: Cat extends Animal ───────────────────────────────────────
class Cat extends Animal {
    private boolean isIndoor;

    Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }

    @Override
    String sound() { return "Meow!"; }

    @Override
    public String toString() {
        return "Cat[name=" + name + ", indoor=" + isIndoor + "]";
    }
}

// ── Grandchild: ServiceDog extends Dog ──────────────────────────────────────
class ServiceDog extends Dog {
    private String task;

    ServiceDog(String name, int age, String breed, String task) {
        super(name, age, breed);
        this.task = task;
    }

    @Override
    String sound() { return "Woof! (trained)"; }

    void performTask() {
        System.out.println(name + " is performing task: " + task);
    }
}

public class Lesson {
    public static void main(String[] args) {
        System.out.println("=== Day 09: Inheritance and Polymorphism ===\n");

        Dog dog     = new Dog("Rex", 3, "Labrador");
        Cat cat     = new Cat("Whiskers", 5, true);
        ServiceDog sd = new ServiceDog("Max", 4, "Golden Retriever", "guide assistance");

        System.out.println("--- Objects ---");
        System.out.println(dog);
        System.out.println(cat);
        System.out.println(sd);
        System.out.println();

        System.out.println("--- Inherited methods ---");
        dog.eat();   // inherited from Animal
        cat.eat();
        System.out.println();

        System.out.println("--- Overridden methods ---");
        dog.describe();   // uses Dog's sound()
        cat.describe();   // uses Cat's sound()
        sd.describe();    // uses ServiceDog's sound()
        System.out.println();

        System.out.println("--- Child-specific methods ---");
        dog.fetch();
        sd.performTask();
        System.out.println();

        // ── POLYMORPHISM ────────────────────────────────────────────────────
        System.out.println("--- Polymorphism ---");
        Animal[] animals = { dog, cat, sd };  // parent type, child objects

        for (Animal a : animals) {
            a.describe();  // calls the OVERRIDDEN version in each subclass
        }
        System.out.println();

        // ── INSTANCEOF ──────────────────────────────────────────────────────
        System.out.println("--- instanceof ---");
        for (Animal a : animals) {
            System.out.print(a.name + " is: ");
            System.out.print(a instanceof Dog ? "Dog " : "");
            System.out.print(a instanceof Cat ? "Cat " : "");
            System.out.print(a instanceof ServiceDog ? "ServiceDog " : "");
            System.out.print(a instanceof Animal ? "Animal" : "");
            System.out.println();
        }

        System.out.println("\n=== End of Day 09 Lesson ===");
    }
}
