package com.javaprogram.validation.controller;

import com.javaprogram.validation.dto.ProductRequest;
import com.javaprogram.validation.entity.Product;
import com.javaprogram.validation.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    ProductController(ProductService service) { this.service = service; }

    @GetMapping                public List<Product> getAll()                               { return service.getAll(); }
    @GetMapping("/{id}")       public Product       getById(@PathVariable Long id)         { return service.getById(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
                               public Product       create(@Valid @RequestBody ProductRequest req) { return service.create(req); }
    @PutMapping("/{id}")       public Product       update(@PathVariable Long id, @Valid @RequestBody ProductRequest req) { return service.update(id, req); }
    @DeleteMapping("/{id}")    @ResponseStatus(HttpStatus.NO_CONTENT)
                               public void          delete(@PathVariable Long id)          { service.delete(id); }
}
