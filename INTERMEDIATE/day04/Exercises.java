/**
 * INTERMEDIATE Day 04 — Streams Deep Dive — Exercises
 */
import java.util.*;
import java.util.stream.*;

record Order(String customer, String item, double total, String status) {}

public class Exercises {

    static List<Order> orders = List.of(
        new Order("Alice", "Laptop",  1200.0, "SHIPPED"),
        new Order("Bob",   "Phone",    700.0, "PENDING"),
        new Order("Alice", "Case",      30.0, "DELIVERED"),
        new Order("Carol", "Tablet",   500.0, "SHIPPED"),
        new Order("Bob",   "Charger",   25.0, "DELIVERED"),
        new Order("Carol", "Laptop",  1200.0, "PENDING"),
        new Order("Alice", "Keyboard", 150.0, "DELIVERED")
    );

    // Exercise 1: Return total revenue from DELIVERED orders.
    static double deliveredRevenue() {
        // TODO: filter DELIVERED, sum totals
        return 0;
    }

    // Exercise 2: Return a Map<String, List<Order>> grouped by customer.
    static Map<String, List<Order>> byCustomer() {
        // TODO: groupingBy customer
        return new HashMap<>();
    }

    // Exercise 3: Return total spent per customer as Map<String, Double>.
    static Map<String, Double> spendPerCustomer() {
        // TODO: groupingBy + summingDouble
        return new HashMap<>();
    }

    // Exercise 4: Return all customer names (unique, sorted alphabetically).
    static List<String> uniqueCustomers() {
        // TODO: map, distinct, sorted, collect
        return new ArrayList<>();
    }

    // Exercise 5: Return comma-separated list of all PENDING item names.
    static String pendingItems() {
        // TODO: filter, map, Collectors.joining(", ")
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Delivered revenue: $" + deliveredRevenue());   // 205.0
        System.out.println("Unique customers: " + uniqueCustomers());       // [Alice,Bob,Carol]
        System.out.println("Pending items: " + pendingItems());             // Phone, Laptop
        System.out.println("Spend per customer: " + spendPerCustomer());
    }
}
