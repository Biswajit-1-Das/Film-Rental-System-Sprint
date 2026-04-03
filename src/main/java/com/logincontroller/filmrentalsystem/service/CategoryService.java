package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.CategoryDTO;
import com.logincontroller.filmrentalsystem.exception.ResourceNotFoundException;
import com.logincontroller.filmrentalsystem.model.Category;
import com.logincontroller.filmrentalsystem.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        if (category.getFilmCategories() != null) {
            dto.setFilmTitles(
                    category.getFilmCategories().stream()
                            .map(fc -> fc.getFilm().getTitle())
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public Category getEntityById(Byte id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> result = new ArrayList<>();

        for (Category category : categories) {
            result.add(toDTO(category));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(Byte id) {
        return toDTO(getEntityById(id));
    }

    @Transactional(readOnly = true)
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + name));
        return toDTO(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategoriesByFilmTitle(String filmTitle) {
        List<Category> categories = categoryRepository.findByFilmCategories_Film_TitleIgnoreCase(filmTitle);
        if (categories.isEmpty()) {
            throw new RuntimeException("No categories found for film: " + filmTitle);
        }
        return categories.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO saveCategory(Category category) {
        return toDTO(categoryRepository.save(category));
    }

    public void deleteCategory(Byte id) {
        categoryRepository.deleteById(id);
    }
}
