package com.pos.dao;

import com.pos.model.Product;

import java.util.ArrayList;
import java.util.List;

// CRUD: Create(add) - Read(get) - Update - Delete
public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products ORDER BY name";

        return products;
    }
}
