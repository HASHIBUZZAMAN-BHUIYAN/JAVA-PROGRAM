package com.javaprogram.validation.service;

import com.javaprogram.validation.dto.ProductRequest;
import com.javaprogram.validation.entity.Product;
import com.javaprogram.validation.exception.ProductNotFoundException;
import com.javaprogram.validation.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;
    ProductService(ProductRepository repo) { this.repo = repo; }

    public List<Product> getAll()       { return repo.findAll(); }

    public Product getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product create(ProductRequest req) {
        return repo.save(new Product(req.getName(), req.getDescription(), req.getPrice(), req.getStock()));
    }

    public Product update(Long id, ProductRequest req) {
        Product p = getById(id);
        p.setName(req.getName()); p.setDescription(req.getDescription());
        p.setPrice(req.getPrice()); p.setStock(req.getStock());
        return repo.save(p);
    }

    public void delete(Long id) { getById(id); repo.deleteById(id); }
}
