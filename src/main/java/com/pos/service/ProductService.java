package com.pos.service;

import com.pos.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    boolean addProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);

    boolean updateStock(int productId, int quantity);

    List<Product> searchProduct(String keyword);
}
