/**
 * INTERMEDIATE Day 08 — Design Patterns I — Solutions
 */
import java.util.*;

class LoggerSol {
    private static LoggerSol instance;
    private List<String> logs = new ArrayList<>();
    private LoggerSol() {}
    static synchronized LoggerSol getInstance() {
        if (instance == null) instance = new LoggerSol();
        return instance;
    }
    void log(String message) { logs.add("[INFO] " + message); }
    List<String> getLogs() { return Collections.unmodifiableList(logs); }
}

interface VehicleSol { String type(); void describe(); }
class CarSol implements VehicleSol {
    @Override public String type() { return "Car"; }
    @Override public void describe() { System.out.println("I am a Car with 4 wheels"); }
}
class TruckSol implements VehicleSol {
    @Override public String type() { return "Truck"; }
    @Override public void describe() { System.out.println("I am a Truck with cargo capacity"); }
}
class MotorcycleSol implements VehicleSol {
    @Override public String type() { return "Motorcycle"; }
    @Override public void describe() { System.out.println("I am a Motorcycle with 2 wheels"); }
}
class VehicleFactorySol {
    static VehicleSol create(String type) {
        return switch (type.toUpperCase()) {
            case "CAR"        -> new CarSol();
            case "TRUCK"      -> new TruckSol();
            case "MOTORCYCLE" -> new MotorcycleSol();
            default -> throw new IllegalArgumentException("Unknown: " + type);
        };
    }
}

class PizzaSol {
    private final String size, crust;
    private final List<String> toppings;
    private final boolean extraCheese;

    private PizzaSol(Builder b) {
        size = b.size; crust = b.crust; toppings = List.copyOf(b.toppings); extraCheese = b.extraCheese;
    }
    @Override public String toString() {
        return "Pizza[size=" + size + ", crust=" + crust + ", toppings=" + toppings + ", extraCheese=" + extraCheese + "]";
    }

    static class Builder {
        private final String size, crust;
        private List<String> toppings = new ArrayList<>();
        private boolean extraCheese = false;
        Builder(String size, String crust) { this.size = size; this.crust = crust; }
        Builder topping(String t) { toppings.add(t); return this; }
        Builder extraCheese() { this.extraCheese = true; return this; }
        PizzaSol build() { return new PizzaSol(this); }
    }
}

public class Solutions {
    public static void main(String[] args) {
        // Singleton
        LoggerSol l1 = LoggerSol.getInstance(), l2 = LoggerSol.getInstance();
        l1.log("App started"); l2.log("User logged in");
        System.out.println("Same logger? " + (l1 == l2));
        System.out.println(l1.getLogs());

        // Factory
        VehicleFactorySol.create("CAR").describe();
        VehicleFactorySol.create("TRUCK").describe();

        // Builder
        PizzaSol p = new PizzaSol.Builder("Large","Thin")
            .topping("mushrooms").topping("peppers").extraCheese().build();
        System.out.println(p);
    }
}
