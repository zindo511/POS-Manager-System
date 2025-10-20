package com.pos.service.impl;

import com.pos.model.Category;
import com.pos.repository.CategoryRepository;
import com.pos.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean addCategory(Category category) {
        if (category == null || category.getName() == null || category.getName().isEmpty()) {
            return false;
        }

        // ignore case
        boolean exits = categoryRepository.findByNameContainingIgnoreCase(category.getName())
                .stream()
                .anyMatch(c -> c.getName().equals(category.getName()));

        if (exits) {
            return false;
        }

        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean updateCategory(Category category) {
        Optional<Category> existingCategory = categoryRepository.findById(category.getId());
        if (existingCategory.isPresent()) {
            Category existing = existingCategory.get();
            existing.setName(category.getName());
            existing.setDescription(category.getDescription());

            categoryRepository.save(existing);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int id) {
        if (!categoryRepository.existsById(id)) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
