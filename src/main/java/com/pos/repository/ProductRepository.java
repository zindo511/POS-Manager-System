package com.pos.repository;

import com.pos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Search for products by name containing keywords (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
