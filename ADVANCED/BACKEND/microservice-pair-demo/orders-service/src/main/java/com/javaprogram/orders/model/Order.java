package com.javaprogram.orders.model;

import java.time.LocalDateTime;

public class Order {
    private String        orderId;
    private String        productId;
    private int           quantity;
    private double        totalPrice;
    private String        status;
    private LocalDateTime createdAt;

    public Order(String orderId, String productId, int quantity, double totalPrice, String status) {
        this.orderId    = orderId;
        this.productId  = productId;
        this.quantity   = quantity;
        this.totalPrice = totalPrice;
        this.status     = status;
        this.createdAt  = LocalDateTime.now();
    }

    public String        getOrderId()    { return orderId; }
    public String        getProductId()  { return productId; }
    public int           getQuantity()   { return quantity; }
    public double        getTotalPrice() { return totalPrice; }
    public String        getStatus()     { return status; }
    public LocalDateTime getCreatedAt()  { return createdAt; }
}
