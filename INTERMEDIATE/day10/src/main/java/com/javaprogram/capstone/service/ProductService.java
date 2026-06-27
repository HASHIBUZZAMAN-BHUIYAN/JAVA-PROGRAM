package com.javaprogram.capstone.service;

import com.javaprogram.capstone.model.Product;
import com.javaprogram.capstone.observer.PriceSubject;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * Service layer for Product operations.
 * Extends PriceSubject to fire observer notifications on price changes.
 * Uses Strategy pattern: caller provides a Comparator for sorting.
 */
public class ProductService extends PriceSubject {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public Optional<Product> findById(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public List<Product> findByCategory(String category) {
        return products.stream()
            .filter(p -> p.getCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }

    public List<Product> findInStock() {
        return products.stream().filter(Product::isInStock).collect(Collectors.toList());
    }

    public List<Product> search(String query) {
        String q = query.toLowerCase();
        return products.stream()
            .filter(p -> p.getName().toLowerCase().contains(q) ||
                         p.getDescription().toLowerCase().contains(q) ||
                         p.getTags().stream().anyMatch(t -> t.contains(q)))
            .collect(Collectors.toList());
    }

    // Strategy pattern: caller supplies the sort strategy as a Comparator
    public List<Product> sortedBy(Comparator<Product> comparator) {
        return products.stream().sorted(comparator).collect(Collectors.toList());
    }

    // Common sort strategies as static constants
    public static final Comparator<Product> BY_PRICE_ASC  = Comparator.comparingDouble(Product::getPrice);
    public static final Comparator<Product> BY_PRICE_DESC = Comparator.comparingDouble(Product::getPrice).reversed();
    public static final Comparator<Product> BY_NAME       = Comparator.comparing(Product::getName);
    public static final Comparator<Product> BY_STOCK_DESC = Comparator.comparingInt(Product::getStock).reversed();

    public double totalInventoryValue() {
        return products.stream().mapToDouble(p -> p.getPrice() * p.getStock()).sum();
    }

    public Map<String, Long> countByCategory() {
        return products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }
}
