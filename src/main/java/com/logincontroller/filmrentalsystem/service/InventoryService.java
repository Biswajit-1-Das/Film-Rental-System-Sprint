package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // --- Basic CRUD ---

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Integer id) {
        inventoryRepository.deleteById(id);
    }

    // --- Simple Filters ---

    public List<Inventory> getInventoryByFilm(Short filmId) {
        return inventoryRepository.findByFilm_FilmId(filmId);
    }

    public List<Inventory> getInventoryByStore(Integer storeId) {
        return inventoryRepository.findByStore_StoreId(storeId);
    }
}