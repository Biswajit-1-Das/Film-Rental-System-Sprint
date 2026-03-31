package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.CategoryController;
import com.logincontroller.filmrentalsystem.dto.CategoryDTO;
import com.logincontroller.filmrentalsystem.model.Category;
import com.logincontroller.filmrentalsystem.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class CategoryTestController {

    @Mock
    CategoryService categoryService;

    CategoryController categoryController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        categoryController = new CategoryController(categoryService);
    }

    @Test
    public void createCategory_delegatesToService() {
        Category input = new Category();
        input.setCategoryId((byte) 1);
        input.setName("Action");

        CategoryDTO expected = new CategoryDTO();
        expected.setCategoryId((byte) 1);
        expected.setName("Action");

        when(categoryService.saveCategory(input)).thenReturn(expected);

        ResponseEntity<CategoryDTO> response = categoryController.createCategory(input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(categoryService).saveCategory(input);
    }

    @Test
    public void deleteCategory_returnsOkMessage() {
        Byte id = 1;

        ResponseEntity<String> response = categoryController.deleteCategory(id);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Category deleted successfully", response.getBody());
        verify(categoryService).deleteCategory(id);
    }
}

