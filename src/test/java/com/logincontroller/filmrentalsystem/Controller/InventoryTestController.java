package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.InventoryController;
import com.logincontroller.filmrentalsystem.dto.InventoryResponseDTO;
import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class InventoryTestController {

    @Mock
    InventoryService inventoryService;

    InventoryController inventoryController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        inventoryController = new InventoryController(inventoryService);
    }

    @Test
    public void add_delegatesAndReturnsDto() {
        Inventory input = new Inventory();
        input.setInventoryId(1);

        InventoryResponseDTO expected = new InventoryResponseDTO();
        expected.setInventoryId(1);

        when(inventoryService.saveInventory(input)).thenReturn(expected);

        ResponseEntity<InventoryResponseDTO> response = inventoryController.add(input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(inventoryService).saveInventory(input);
    }

    @Test
    public void delete_returnsNoContent() {
        Integer id = 1;
        doNothing().when(inventoryService).deleteInventory(id);

        ResponseEntity<Void> response = inventoryController.delete(id);

        assertEquals(204, response.getStatusCode().value());
        verify(inventoryService).deleteInventory(id);
    }
}

