package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Category;
import com.logincontroller.filmrentalsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public Category createCategory(Category category) {
        category.setLastUpdate(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Integer id, Category categoryDetails) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(categoryDetails.getName());
        existingCategory.setLastUpdate(LocalDateTime.now());
        return categoryRepository.save(existingCategory);
    }

    public Category patchCategory(Integer id, Category categoryUpdates) {
        Category existingCategory = getCategoryById(id);
        if (categoryUpdates.getName() != null) {
            existingCategory.setName(categoryUpdates.getName());
        }
        existingCategory.setLastUpdate(LocalDateTime.now());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}