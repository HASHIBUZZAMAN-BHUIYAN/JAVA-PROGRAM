/**
 * INTERMEDIATE Day 04 — Streams — Solutions
 */
import java.util.*;
import java.util.stream.*;

record OrderSol(String customer, String item, double total, String status) {}

public class Solutions {

    static List<OrderSol> orders = List.of(
        new OrderSol("Alice", "Laptop",  1200.0, "SHIPPED"),
        new OrderSol("Bob",   "Phone",    700.0, "PENDING"),
        new OrderSol("Alice", "Case",      30.0, "DELIVERED"),
        new OrderSol("Carol", "Tablet",   500.0, "SHIPPED"),
        new OrderSol("Bob",   "Charger",   25.0, "DELIVERED"),
        new OrderSol("Carol", "Laptop",  1200.0, "PENDING"),
        new OrderSol("Alice", "Keyboard", 150.0, "DELIVERED")
    );

    static double deliveredRevenue() {
        return orders.stream().filter(o -> o.status().equals("DELIVERED"))
            .mapToDouble(OrderSol::total).sum();
    }

    static Map<String, List<OrderSol>> byCustomer() {
        return orders.stream().collect(Collectors.groupingBy(OrderSol::customer));
    }

    static Map<String, Double> spendPerCustomer() {
        return orders.stream().collect(
            Collectors.groupingBy(OrderSol::customer, Collectors.summingDouble(OrderSol::total)));
    }

    static List<String> uniqueCustomers() {
        return orders.stream().map(OrderSol::customer).distinct().sorted().collect(Collectors.toList());
    }

    static String pendingItems() {
        return orders.stream().filter(o -> o.status().equals("PENDING"))
            .map(OrderSol::item).collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        System.out.println("Delivered revenue: $" + deliveredRevenue());
        System.out.println("Unique customers: " + uniqueCustomers());
        System.out.println("Pending items: " + pendingItems());
        System.out.println("Spend per customer: " + spendPerCustomer());
    }
}
