package com.javaprogram.capstone.model;

import java.util.*;

/**
 * Product model using the Builder pattern.
 * Immutable after construction — all fields set via builder.
 */
public class Product {
    private final String id;
    private final String name;
    private final String category;
    private final double price;
    private final int    stock;
    private final String description;
    private final List<String> tags;

    private Product(Builder b) {
        this.id          = b.id;
        this.name        = b.name;
        this.category    = b.category;
        this.price       = b.price;
        this.stock       = b.stock;
        this.description = b.description;
        this.tags        = List.copyOf(b.tags);
    }

    public String getId()          { return id; }
    public String getName()        { return name; }
    public String getCategory()    { return category; }
    public double getPrice()       { return price; }
    public int    getStock()       { return stock; }
    public String getDescription() { return description; }
    public List<String> getTags()  { return tags; }

    public boolean isInStock()     { return stock > 0; }

    @Override
    public String toString() {
        return String.format("Product{id='%s', name='%s', category='%s', price=%.2f, stock=%d}",
                             id, name, category, price, stock);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product p)) return false;
        return id.equals(p.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }

    public static class Builder {
        private final String id;
        private final String name;
        private String       category    = "General";
        private double       price       = 0.0;
        private int          stock       = 0;
        private String       description = "";
        private List<String> tags        = new ArrayList<>();

        public Builder(String id, String name) {
            if (id == null || id.isBlank())   throw new IllegalArgumentException("id required");
            if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
            this.id   = id;
            this.name = name;
        }

        public Builder category(String c)    { this.category = c; return this; }
        public Builder price(double p)       {
            if (p < 0) throw new IllegalArgumentException("price cannot be negative");
            this.price = p; return this;
        }
        public Builder stock(int s)          {
            if (s < 0) throw new IllegalArgumentException("stock cannot be negative");
            this.stock = s; return this;
        }
        public Builder description(String d) { this.description = d; return this; }
        public Builder tag(String t)         { this.tags.add(t); return this; }

        public Product build()               { return new Product(this); }
    }
}
