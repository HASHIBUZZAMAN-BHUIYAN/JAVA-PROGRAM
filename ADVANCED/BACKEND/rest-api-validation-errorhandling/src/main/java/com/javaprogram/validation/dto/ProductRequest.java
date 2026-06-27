package com.javaprogram.validation.dto;

import jakarta.validation.constraints.*;

/**
 * DTO with Bean Validation annotations.
 * Spring will validate this before calling the controller method when @Valid is used.
 */
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Name must be 2–100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Positive(message = "Price must be greater than zero")
    private double price;

    @PositiveOrZero(message = "Stock cannot be negative")
    private int stock;

    public String getName()        { return name; }
    public String getDescription() { return description; }
    public double getPrice()       { return price; }
    public int    getStock()       { return stock; }

    public void setName(String n)          { this.name = n; }
    public void setDescription(String d)   { this.description = d; }
    public void setPrice(double p)         { this.price = p; }
    public void setStock(int s)            { this.stock = s; }
}
