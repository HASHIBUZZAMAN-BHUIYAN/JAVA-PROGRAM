/**
 * INTERMEDIATE Day 08 — Design Patterns I — Exercises
 */
import java.util.*;

// Exercise 1: Implement a Logger Singleton that appends log messages to a list.
class Logger {
    // TODO: make Logger a Singleton with a log(String message) method
    // and a getLogs() method returning the list of messages.
    // Each message should be prefixed with "[INFO] "
}

// Exercise 2: Implement a Vehicle Factory.
// Vehicle types: "CAR", "TRUCK", "MOTORCYCLE" — each has a String type() and
// void describe() method that prints: "I am a {type} with {feature}"
interface Vehicle {
    String type();
    void describe();
}

class VehicleFactory {
    // TODO: static Vehicle create(String type) factory method
}

// Exercise 3: Implement a Pizza Builder.
// Pizza has: size (required), crust (required), List<String> toppings, boolean extraCheese
class Pizza {
    // TODO: implement Pizza with a static nested Builder class
    // Builder should support: size(String), crust(String), topping(String), extraCheese()
    // toString should print: "Pizza[size=Large, crust=Thin, toppings=[mushrooms,peppers], extraCheese=true]"
}

public class Exercises {
    public static void main(String[] args) {
        // Test Exercise 1
        // Logger log1 = Logger.getInstance();
        // Logger log2 = Logger.getInstance();
        // log1.log("App started");
        // log2.log("User logged in");
        // System.out.println(log1 == log2); // true
        // System.out.println(log1.getLogs()); // [INFO] App started, ...

        // Test Exercise 2
        // Vehicle car  = VehicleFactory.create("CAR");
        // Vehicle truck = VehicleFactory.create("TRUCK");
        // car.describe();    // I am a Car with 4 wheels
        // truck.describe();  // I am a Truck with cargo capacity

        // Test Exercise 3
        // Pizza p = new Pizza.Builder("Large","Thin")
        //     .topping("mushrooms").topping("peppers").extraCheese().build();
        // System.out.println(p);

        System.out.println("Uncomment the test code above after implementing the classes.");
    }
}
