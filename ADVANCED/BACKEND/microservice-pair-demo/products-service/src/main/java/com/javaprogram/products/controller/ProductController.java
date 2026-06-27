package com.javaprogram.products.controller;

import com.javaprogram.products.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Products service (port 8081) — provides product catalog.
 * Orders service calls this via HTTP.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // In-memory "database" for demo purposes
    private final Map<String, Product> products = new LinkedHashMap<>();

    public ProductController() {
        products.put("P001", new Product("P001", "Laptop",     999.99, 15));
        products.put("P002", new Product("P002", "Phone",      699.99, 40));
        products.put("P003", new Product("P003", "Headphones", 199.99, 60));
        products.put("P004", new Product("P004", "Keyboard",    89.99, 100));
    }

    @GetMapping
    public List<Product> getAll() { return new ArrayList<>(products.values()); }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) {
        Product p = products.get(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/check-stock/{id}")
    public ResponseEntity<Boolean> checkStock(@PathVariable String id, @RequestParam int quantity) {
        Product p = products.get(id);
        if (p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p.stock() >= quantity);
    }
}
