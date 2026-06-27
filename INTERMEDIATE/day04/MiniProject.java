/**
 * INTERMEDIATE Day 04 Mini Project — E-Commerce Analytics Dashboard
 * Full stream-based analytics on a product + order dataset.
 *
 * Run: java MiniProject
 */
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

record AnalyticsOrder(String orderId, String customer, String product,
                      String category, double price, int qty, String month) {}

public class MiniProject {

    public static void main(String[] args) {
        List<AnalyticsOrder> orders = List.of(
            new AnalyticsOrder("O001","Alice","Laptop","Electronics",999.99,1,"Jan"),
            new AnalyticsOrder("O002","Bob","Chair","Furniture",149.99,2,"Jan"),
            new AnalyticsOrder("O003","Alice","Phone","Electronics",699.99,1,"Feb"),
            new AnalyticsOrder("O004","Carol","Laptop","Electronics",999.99,1,"Feb"),
            new AnalyticsOrder("O005","Dave","Desk","Furniture",249.99,1,"Feb"),
            new AnalyticsOrder("O006","Bob","Headphones","Electronics",199.99,3,"Mar"),
            new AnalyticsOrder("O007","Carol","Lamp","Furniture",49.99,5,"Mar"),
            new AnalyticsOrder("O008","Alice","Tablet","Electronics",449.99,2,"Mar"),
            new AnalyticsOrder("O009","Dave","Chair","Furniture",149.99,1,"Apr"),
            new AnalyticsOrder("O010","Eve","Laptop","Electronics",999.99,1,"Apr")
        );

        Function<AnalyticsOrder,Double> revenue = o -> o.price() * o.qty();

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   E-Commerce Analytics Dashboard     ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        // Total revenue
        double totalRev = orders.stream().mapToDouble(revenue::apply).sum();
        System.out.printf("Total Revenue: $%.2f%n", totalRev);
        System.out.printf("Total Orders:  %d%n%n", orders.size());

        // Revenue by category
        System.out.println("--- Revenue by Category ---");
        orders.stream()
            .collect(Collectors.groupingBy(AnalyticsOrder::category,
                     Collectors.summingDouble(revenue::apply)))
            .entrySet().stream()
            .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
            .forEach(e -> System.out.printf("  %-15s $%.2f%n", e.getKey(), e.getValue()));

        // Revenue by month
        System.out.println("\n--- Monthly Revenue ---");
        List<String> months = List.of("Jan","Feb","Mar","Apr");
        Map<String,Double> byMonth = orders.stream()
            .collect(Collectors.groupingBy(AnalyticsOrder::month,
                     Collectors.summingDouble(revenue::apply)));
        months.stream()
            .filter(byMonth::containsKey)
            .forEach(m -> System.out.printf("  %-5s $%.2f%n", m, byMonth.get(m)));

        // Top customers
        System.out.println("\n--- Top Customers ---");
        orders.stream()
            .collect(Collectors.groupingBy(AnalyticsOrder::customer,
                     Collectors.summingDouble(revenue::apply)))
            .entrySet().stream()
            .sorted(Map.Entry.<String,Double>comparingByValue().reversed())
            .limit(3)
            .forEach(e -> System.out.printf("  %-10s $%.2f%n", e.getKey(), e.getValue()));

        // Top products
        System.out.println("\n--- Best-Selling Products (by units) ---");
        orders.stream()
            .collect(Collectors.groupingBy(AnalyticsOrder::product,
                     Collectors.summingInt(AnalyticsOrder::qty)))
            .entrySet().stream()
            .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
            .limit(3)
            .forEach(e -> System.out.printf("  %-15s %d units%n", e.getKey(), e.getValue()));

        // Average order value
        OptionalDouble avg = orders.stream().mapToDouble(revenue::apply).average();
        System.out.printf("%nAverage Order Value: $%.2f%n", avg.orElse(0));
    }
}
