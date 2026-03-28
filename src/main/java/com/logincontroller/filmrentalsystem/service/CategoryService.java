package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.CategoryDTO;
import com.logincontroller.filmrentalsystem.model.Category;
import com.logincontroller.filmrentalsystem.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());
        dto.setLastUpdate(category.getLastUpdate());
        if (category.getFilms() != null) {
            dto.setFilmTitles(
                    category.getFilms().stream()
                            .map(film -> film.getTitle())
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public Category getEntityById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Integer id) {
        return toDTO(getEntityById(id));
    }

    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Category not found: " + name));
        return toDTO(category);
    }

    public CategoryDTO saveCategory(Category category) {
        return toDTO(categoryRepository.save(category));
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}