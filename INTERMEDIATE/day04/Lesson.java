/**
 * INTERMEDIATE Day 04 — Streams API Deep Dive
 */
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

record Product(String name, String category, double price, int stock) {}

public class Lesson {

    public static void main(String[] args) {
        System.out.println("=== INTERMEDIATE Day 04: Streams Deep Dive ===\n");

        List<Product> products = List.of(
            new Product("Laptop",     "Electronics", 999.99, 15),
            new Product("Phone",      "Electronics", 699.99, 40),
            new Product("Desk",       "Furniture",   249.99, 8),
            new Product("Chair",      "Furniture",   149.99, 25),
            new Product("Headphones", "Electronics", 199.99, 60),
            new Product("Bookshelf",  "Furniture",   179.99, 5),
            new Product("Tablet",     "Electronics", 449.99, 20),
            new Product("Lamp",       "Furniture",    49.99, 50)
        );

        // ── 1. collect() to toList / toSet ───────────────────────────────────
        System.out.println("--- 1. collect() ---");
        List<String> electronicNames = products.stream()
            .filter(p -> p.category().equals("Electronics"))
            .map(Product::name)
            .sorted()
            .collect(Collectors.toList());
        System.out.println("Electronics: " + electronicNames);

        // Collectors.joining() — join strings
        String joined = products.stream()
            .map(Product::name)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Joined: " + joined);
        System.out.println();

        // ── 2. toMap() ───────────────────────────────────────────────────────
        System.out.println("--- 2. toMap() ---");
        Map<String, Double> nameToPrice = products.stream()
            .collect(Collectors.toMap(Product::name, Product::price));
        System.out.println("Laptop price: " + nameToPrice.get("Laptop"));
        System.out.println();

        // ── 3. groupingBy() ──────────────────────────────────────────────────
        System.out.println("--- 3. groupingBy() ---");
        Map<String, List<Product>> byCategory = products.stream()
            .collect(Collectors.groupingBy(Product::category));

        byCategory.forEach((cat, prods) -> {
            System.out.println(cat + ":");
            prods.forEach(p -> System.out.printf("  %-15s $%.2f%n", p.name(), p.price()));
        });
        System.out.println();

        // ── 4. groupingBy() with downstream collectors ────────────────────────
        System.out.println("--- 4. groupingBy() + downstream ---");

        // Count per category
        Map<String, Long> countByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::category, Collectors.counting()));
        System.out.println("Count per category: " + countByCategory);

        // Average price per category
        Map<String, Double> avgPriceByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::category,
                     Collectors.averagingDouble(Product::price)));
        avgPriceByCategory.forEach((k,v) -> System.out.printf("Avg price %-12s: $%.2f%n", k, v));

        // Total stock value per category
        Map<String, Double> stockValueByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::category,
                     Collectors.summingDouble(p -> p.price() * p.stock())));
        stockValueByCategory.forEach((k,v) -> System.out.printf("Stock value %-12s: $%.2f%n", k, v));
        System.out.println();

        // ── 5. reduce() ──────────────────────────────────────────────────────
        System.out.println("--- 5. reduce() ---");
        double totalInventoryValue = products.stream()
            .mapToDouble(p -> p.price() * p.stock())
            .sum();
        System.out.printf("Total inventory value: $%.2f%n", totalInventoryValue);

        Optional<Product> mostExpensive = products.stream()
            .reduce((a, b) -> a.price() > b.price() ? a : b);
        mostExpensive.ifPresent(p -> System.out.println("Most expensive: " + p.name() + " $" + p.price()));
        System.out.println();

        // ── 6. flatMap() ─────────────────────────────────────────────────────
        System.out.println("--- 6. flatMap() ---");
        List<List<Integer>> nested = List.of(List.of(1,2,3), List.of(4,5), List.of(6,7,8,9));
        List<Integer> flat = nested.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        System.out.println("Flattened: " + flat);
        System.out.println();

        // ── 7. findFirst / anyMatch / allMatch / noneMatch ───────────────────
        System.out.println("--- 7. match / find ---");
        System.out.println("Any price < 100: " + products.stream().anyMatch(p -> p.price() < 100));
        System.out.println("All in stock: "    + products.stream().allMatch(p -> p.stock() > 0));
        Optional<Product> cheap = products.stream().filter(p -> p.price() < 100).findFirst();
        cheap.ifPresent(p -> System.out.println("Cheapest < $100: " + p.name()));
        System.out.println();

        // ── 8. Parallel streams (note: best for large CPU-bound data) ─────────
        System.out.println("--- 8. Parallel stream ---");
        long count = products.parallelStream()
            .filter(p -> p.price() > 200)
            .count();
        System.out.println("Products > $200 (parallel): " + count);

        System.out.println("\n=== End of Day 04 Lesson ===");
    }
}
