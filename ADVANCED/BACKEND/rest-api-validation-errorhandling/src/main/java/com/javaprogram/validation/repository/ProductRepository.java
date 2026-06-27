package com.javaprogram.validation.repository;

import com.javaprogram.validation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
