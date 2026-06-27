package com.javaprogram.capstone;

import com.javaprogram.capstone.factory.ProductFactory;
import com.javaprogram.capstone.model.Product;
import com.javaprogram.capstone.observer.PriceObserver;
import com.javaprogram.capstone.service.ProductService;

/**
 * INTERMEDIATE Day 10 Capstone — Main entry point
 * Run: mvn exec:java -Dexec.mainClass="com.javaprogram.capstone.Main"
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== INTERMEDIATE Day 10: Capstone Maven Project ===\n");

        // ── Set up service ────────────────────────────────────────────────────
        ProductService service = new ProductService();

        // Register price change observer (lambda — Observer pattern)
        PriceObserver priceLogger = (id, name, old, newPrice) ->
            System.out.printf("  [PRICE CHANGE] %s: $%.2f → $%.2f%n", name, old, newPrice);
        service.addObserver(priceLogger);

        // ── Add products via Factory ──────────────────────────────────────────
        service.addProduct(ProductFactory.createElectronics("E001", "Laptop",     999.99, 15));
        service.addProduct(ProductFactory.createElectronics("E002", "Phone",      699.99, 40));
        service.addProduct(ProductFactory.createElectronics("E003", "Headphones", 199.99, 60));
        service.addProduct(ProductFactory.createFurniture  ("F001", "Desk",       249.99, 8));
        service.addProduct(ProductFactory.createFurniture  ("F002", "Chair",      149.99, 25));
        service.addProduct(ProductFactory.createBook       ("B001", "Effective Java", "Joshua Bloch", 49.99));
        service.addProduct(ProductFactory.createBook       ("B002", "Clean Code",     "Robert Martin", 44.99));

        // Add one via Builder directly (more control)
        service.addProduct(new Product.Builder("E004", "Gaming Mouse")
            .category("Electronics")
            .price(79.99)
            .stock(100)
            .description("High-precision wireless gaming mouse")
            .tag("gaming").tag("electronics").tag("accessories")
            .build());

        // ── Queries ────────────────────────────────────────────────────────────
        System.out.println("--- All Products (sorted by price asc) ---");
        service.sortedBy(ProductService.BY_PRICE_ASC)
            .forEach(p -> System.out.printf("  %-20s $%7.2f  (%d in stock)%n",
                p.getName(), p.getPrice(), p.getStock()));

        System.out.println("\n--- Electronics only ---");
        service.findByCategory("Electronics")
            .forEach(p -> System.out.println("  " + p.getName()));

        System.out.println("\n--- Search 'java' ---");
        service.search("java")
            .forEach(p -> System.out.println("  " + p.getName() + " - " + p.getDescription()));

        System.out.println("\n--- Stats ---");
        System.out.printf("  Total inventory value: $%.2f%n", service.totalInventoryValue());
        System.out.println("  Count by category: " + service.countByCategory());

        System.out.println("\n=== End of Capstone ===");
    }
}
