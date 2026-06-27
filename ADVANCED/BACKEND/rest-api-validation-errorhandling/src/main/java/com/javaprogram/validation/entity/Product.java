package com.javaprogram.validation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int    stock;

    public Product() {}
    public Product(String name, String description, double price, int stock) {
        this.name = name; this.description = description;
        this.price = price; this.stock = stock;
    }

    public Long   getId()          { return id; }
    public String getName()        { return name; }
    public String getDescription() { return description; }
    public double getPrice()       { return price; }
    public int    getStock()       { return stock; }

    public void setName(String n)          { this.name = n; }
    public void setDescription(String d)   { this.description = d; }
    public void setPrice(double p)         { this.price = p; }
    public void setStock(int s)            { this.stock = s; }
}
