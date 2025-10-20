package com.pos.service;

import com.pos.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int id);
}
