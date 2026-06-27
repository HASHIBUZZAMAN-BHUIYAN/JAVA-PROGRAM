/**
 * INTERMEDIATE Day 03 Mini Project — Functional Pipeline for Sales Data
 * Applies: lambdas, Function, Predicate, Consumer, method references, streams.
 *
 * Run: java MiniProject
 */
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

record Sale(String region, String product, double amount, String salesperson) {}

public class MiniProject {

    public static void main(String[] args) {
        List<Sale> sales = List.of(
            new Sale("North", "Widget A", 1500.0, "Alice"),
            new Sale("South", "Widget B", 2200.0, "Bob"),
            new Sale("North", "Widget A", 900.0,  "Carol"),
            new Sale("East",  "Gadget X", 3100.0, "Alice"),
            new Sale("South", "Gadget X", 850.0,  "Dave"),
            new Sale("East",  "Widget B", 1200.0, "Bob"),
            new Sale("North", "Gadget X", 4500.0, "Carol"),
            new Sale("West",  "Widget A", 2800.0, "Dave"),
            new Sale("West",  "Widget B", 1750.0, "Alice")
        );

        // Predicate: high value sales
        Predicate<Sale> isHighValue = s -> s.amount() >= 2000;

        // Function: format a sale summary
        Function<Sale, String> formatSale = s ->
            String.format("%-8s %-10s %-12s $%.2f", s.region(), s.product(), s.salesperson(), s.amount());

        // Consumer: print a section header
        Consumer<String> printHeader = title -> {
            System.out.println("\n=== " + title + " ===");
            System.out.printf("%-8s %-10s %-12s %s%n", "Region", "Product", "Salesperson", "Amount");
            System.out.println("-".repeat(48));
        };

        // 1. All sales
        printHeader.accept("All Sales");
        sales.stream().map(formatSale).forEach(System.out::println);

        // 2. High-value sales using Predicate
        printHeader.accept("High Value Sales (>= $2000)");
        sales.stream().filter(isHighValue).map(formatSale).forEach(System.out::println);

        // 3. Total by region — groupingBy + summing
        System.out.println("\n=== Sales by Region ===");
        sales.stream()
            .collect(Collectors.groupingBy(Sale::region,
                     Collectors.summingDouble(Sale::amount)))
            .entrySet().stream()
            .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
            .forEach(e -> System.out.printf("%-8s $%.2f%n", e.getKey(), e.getValue()));

        // 4. Top salesperson
        System.out.println("\n=== Salesperson Totals ===");
        sales.stream()
            .collect(Collectors.groupingBy(Sale::salesperson,
                     Collectors.summingDouble(Sale::amount)))
            .entrySet().stream()
            .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
            .forEach(e -> System.out.printf("%-12s $%.2f%n", e.getKey(), e.getValue()));

        // 5. Compose predicates
        Predicate<Sale> northOrEast = s -> s.region().equals("North") || s.region().equals("East");
        Predicate<Sale> northEastHighValue = northOrEast.and(isHighValue);

        System.out.println("\n=== North/East High-Value Sales ===");
        sales.stream().filter(northEastHighValue).map(formatSale).forEach(System.out::println);
    }
}
