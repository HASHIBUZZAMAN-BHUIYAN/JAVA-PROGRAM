package com.javaprogram.capstone.factory;

import com.javaprogram.capstone.model.Product;

/**
 * Factory for creating pre-configured Product objects.
 */
public class ProductFactory {

    public static Product createElectronics(String id, String name, double price, int stock) {
        return new Product.Builder(id, name)
            .category("Electronics")
            .price(price)
            .stock(stock)
            .tag("electronics")
            .tag("tech")
            .build();
    }

    public static Product createFurniture(String id, String name, double price, int stock) {
        return new Product.Builder(id, name)
            .category("Furniture")
            .price(price)
            .stock(stock)
            .tag("furniture")
            .tag("home")
            .build();
    }

    public static Product createBook(String id, String title, String author, double price) {
        return new Product.Builder(id, title)
            .category("Books")
            .price(price)
            .stock(999)  // books usually have high stock
            .description("By " + author)
            .tag("book")
            .tag("education")
            .build();
    }
}
