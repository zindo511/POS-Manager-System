package com.pos.service.impl;

import com.pos.model.Product;
import com.pos.repository.ProductRepository;
import com.pos.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    public final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean addProduct(Product product) {
        if (product == null || product.getName() == null || product.getName().isEmpty()) {
            return false;
        }

        // Check if product already exists by name
        boolean exists = productRepository.findByNameContainingIgnoreCase(product.getName())
                .stream().anyMatch(p -> p.getName().equals(product.getName()));

        if (exists) {
            return false;
        }

        productRepository.save(product);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getId());
        if (existingProduct.isPresent()) {
            Product product1 = existingProduct.get();
            product1.setName(product.getName());
            product1.setCategory(product.getCategory());
            product1.setPrice(product.getPrice());
            product1.setStock(product.getStock());
            product1.setImagePath(product.getImagePath());
            productRepository.save(product1);

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStock(int productId, int quantity) {
        // Get products from database
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isEmpty()) {
            return false;
        }

        Product product = existingProduct.get();

        // Check quantity (if quantity is negative, subtract inventory)
        int newStock = product.getStock() + quantity; // +quantity: inventory increases, -quantity: inventory decrease
        if (newStock < 0) {
            return false;
        }

        // Update inventory
        product.setStock(newStock);

        // Save to database
        productRepository.save(product);
        return true;
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            // If there is no keyword, return all products
            return productRepository.findAll();
        }
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}
