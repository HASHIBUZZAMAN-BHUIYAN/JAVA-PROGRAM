/**
 * Day 10 — OOP III: Abstract Classes and Interfaces
 */

// ── ABSTRACT CLASS — can't be instantiated directly ─────────────────────────
abstract class Vehicle {
    protected String make;
    protected String model;
    protected int    year;

    Vehicle(String make, String model, int year) {
        this.make  = make;
        this.model = model;
        this.year  = year;
    }

    // Concrete method — shared by all subclasses
    void displayInfo() {
        System.out.printf("[%d %s %s]%n", year, make, model);
    }

    // Abstract method — MUST be implemented by every subclass
    abstract double fuelCostPerKm();
    abstract String fuelType();
}

// ── Concrete subclasses ──────────────────────────────────────────────────────
class GasCar extends Vehicle {
    private double litersPer100km;
    private double gasPricePerLiter;

    GasCar(String make, String model, int year,
           double litersPer100km, double gasPricePerLiter) {
        super(make, model, year);
        this.litersPer100km   = litersPer100km;
        this.gasPricePerLiter = gasPricePerLiter;
    }

    @Override public double fuelCostPerKm() {
        return (litersPer100km / 100) * gasPricePerLiter;
    }
    @Override public String fuelType() { return "Gasoline"; }
}

class ElectricCar extends Vehicle {
    private double kwhPer100km;
    private double electricityPricePerKwh;

    ElectricCar(String make, String model, int year,
                double kwhPer100km, double electricityPricePerKwh) {
        super(make, model, year);
        this.kwhPer100km             = kwhPer100km;
        this.electricityPricePerKwh  = electricityPricePerKwh;
    }

    @Override public double fuelCostPerKm() {
        return (kwhPer100km / 100) * electricityPricePerKwh;
    }
    @Override public String fuelType() { return "Electric"; }
}

// ── INTERFACE — defines a capability/contract ─────────────────────────────────
interface Printable {
    void print();  // implicitly public abstract
}

interface Serializable {
    String serialize();
    // default method (Java 8+) — provides an implementation
    default void printSerialized() {
        System.out.println("Serialized: " + serialize());
    }
}

// A class CAN implement multiple interfaces
class Report implements Printable, Serializable {
    private String title;
    private String content;

    Report(String title, String content) {
        this.title   = title;
        this.content = content;
    }

    @Override public void print() {
        System.out.println("=== " + title + " ===");
        System.out.println(content);
    }

    @Override public String serialize() {
        return "{title:\"" + title + "\",content:\"" + content + "\"}";
    }
}

// ── INTERFACE as TYPE ─────────────────────────────────────────────────────────
interface Describable {
    String describe();
}

// GasCar can also implement an interface (class can extend ONE + implement MANY)
class DescribableGasCar extends GasCar implements Describable {
    DescribableGasCar(String make, String model, int year,
                      double litersPer100km, double gasPricePerLiter) {
        super(make, model, year, litersPer100km, gasPricePerLiter);
    }

    @Override
    public String describe() {
        return String.format("%d %s %s — %s, $%.4f/km",
               year, make, model, fuelType(), fuelCostPerKm());
    }
}

public class Lesson {
    public static void main(String[] args) {
        System.out.println("=== Day 10: Abstract Classes and Interfaces ===\n");

        // ── Abstract class usage ─────────────────────────────────────────────
        Vehicle[] fleet = {
            new GasCar("Toyota", "Camry", 2022, 8.5, 1.85),
            new ElectricCar("Tesla", "Model 3", 2023, 14.0, 0.25),
            new GasCar("Honda", "Civic", 2021, 6.5, 1.85)
        };

        System.out.println("Vehicle fleet cost comparison (per km):");
        for (Vehicle v : fleet) {
            v.displayInfo();
            System.out.printf("  Fuel: %-10s Cost/km: $%.4f%n",
                              v.fuelType(), v.fuelCostPerKm());
        }
        System.out.println();

        // ── Interface usage ──────────────────────────────────────────────────
        Report r = new Report("Sales Summary", "Q1 total: $1.2M");
        r.print();
        r.printSerialized(); // default method from Serializable

        System.out.println();
        DescribableGasCar fancy = new DescribableGasCar("BMW", "330i", 2023, 9.0, 1.95);
        System.out.println(fancy.describe());

        System.out.println("\n--- Key Rules ---");
        System.out.println("abstract class: use when subclasses share CODE");
        System.out.println("interface: use when unrelated classes share a CONTRACT");
        System.out.println("A class can extend only 1 class, but implement many interfaces.");
        System.out.println("\n=== End of Day 10 Lesson ===");
    }
}
