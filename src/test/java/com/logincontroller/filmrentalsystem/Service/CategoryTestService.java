package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.CategoryDTO;
import com.logincontroller.filmrentalsystem.model.Category;
import com.logincontroller.filmrentalsystem.repository.CategoryRepository;
import com.logincontroller.filmrentalsystem.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CategoryTestService {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCategoryByName_mapsToDto() {
        String name = "Action";
        Byte id = 1;

        Category entity = new Category();
        entity.setCategoryId(id);
        entity.setName(name);
        entity.setLastUpdate(LocalDateTime.now());

        when(categoryRepository.findByNameIgnoreCase(name)).thenReturn(Optional.of(entity));

        CategoryDTO dto = categoryService.getCategoryByName(name);

        assertEquals(id, dto.getCategoryId());
        assertEquals(name, dto.getName());
        verify(categoryRepository).findByNameIgnoreCase(name);
    }

    @Test
    public void saveCategory_savesAndReturnsDto() {
        Category input = new Category();
        input.setCategoryId((byte) 1);
        input.setName("Drama");
        input.setLastUpdate(LocalDateTime.now());

        when(categoryRepository.save(input)).thenReturn(input);

        CategoryDTO dto = categoryService.saveCategory(input);

        assertEquals("Drama", dto.getName());
        verify(categoryRepository).save(input);
    }

    @Test
    public void deleteCategory_deletesById() {
        Byte id = 1;
        doNothing().when(categoryRepository).deleteById(id);

        categoryService.deleteCategory(id);

        verify(categoryRepository).deleteById(id);
    }
}

