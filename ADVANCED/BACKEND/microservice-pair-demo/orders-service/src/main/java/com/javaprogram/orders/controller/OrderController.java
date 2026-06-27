package com.javaprogram.orders.controller;

import com.javaprogram.orders.model.Order;
import com.javaprogram.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;
    OrderController(OrderService service) { this.service = service; }

    @GetMapping
    public List<Order> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable String id) {
        Order o = service.findById(id);
        return o != null ? ResponseEntity.ok(o) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order placeOrder(@RequestBody Map<String, Object> req) {
        String productId = (String) req.get("productId");
        int    quantity  = (int) req.get("quantity");
        return service.placeOrder(productId, quantity);
    }
}
