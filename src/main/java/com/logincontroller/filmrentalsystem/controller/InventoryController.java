package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Integer id) {
        return inventoryService.getInventoryById(id);
    }

    // Example: /api/inventory/film/5
    @GetMapping("/film/{filmId}")
    public List<Inventory> getInventoryByFilm(@PathVariable Short filmId) {
        return inventoryService.getInventoryByFilm(filmId);
    }

    // Example: /api/inventory/store/1
    @GetMapping("/store/{storeId}")
    public List<Inventory> getInventoryByStore(@PathVariable Integer storeId) {
        return inventoryService.getInventoryByStore(storeId);
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
    }
}