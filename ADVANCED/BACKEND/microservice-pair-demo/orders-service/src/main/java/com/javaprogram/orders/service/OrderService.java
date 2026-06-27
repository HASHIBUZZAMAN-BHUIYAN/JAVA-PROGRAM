package com.javaprogram.orders.service;

import com.javaprogram.orders.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Orders service calls the Products service via HTTP using RestTemplate.
 * This is the microservice communication pattern.
 */
@Service
public class OrderService {

    private final RestTemplate restTemplate;
    private final String       productsBaseUrl;
    private final Map<String, Order> orders = new LinkedHashMap<>();
    private int counter = 1;

    OrderService(RestTemplate restTemplate,
                 @Value("${products.service.url}") String productsBaseUrl) {
        this.restTemplate   = restTemplate;
        this.productsBaseUrl = productsBaseUrl;
    }

    public List<Order> getAll() { return new ArrayList<>(orders.values()); }

    public Order findById(String id) { return orders.get(id); }

    @SuppressWarnings("unchecked")
    public Order placeOrder(String productId, int quantity) {
        // 1. Call products-service to get price
        String productUrl = productsBaseUrl + "/api/products/" + productId;
        Map<String, Object> product;
        try {
            product = restTemplate.getForObject(productUrl, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Products service unavailable: " + e.getMessage());
        }

        if (product == null) throw new RuntimeException("Product not found: " + productId);

        double price      = ((Number) product.get("price")).doubleValue();
        double totalPrice = price * quantity;

        // 2. Check stock
        String stockUrl = productsBaseUrl + "/api/products/check-stock/" + productId + "?quantity=" + quantity;
        Boolean inStock = restTemplate.getForObject(stockUrl, Boolean.class);
        String status = Boolean.TRUE.equals(inStock) ? "CONFIRMED" : "PENDING_STOCK";

        String orderId = "ORD-" + String.format("%03d", counter++);
        Order order = new Order(orderId, productId, quantity, totalPrice, status);
        orders.put(orderId, order);
        return order;
    }
}
