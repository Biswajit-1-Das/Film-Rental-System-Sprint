package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.model.Stores;
import com.logincontroller.filmrentalsystem.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    /**
     * Get all inventory items
     */
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    /**
     * Get inventory by ID
     */
    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
    }

    /**
     * Get inventory with film and store details
     */
    public Inventory getInventoryWithDetails(Integer id) {
        return inventoryRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
    }

    /**
     * Get all inventory for a specific film
     */
    public List<Inventory> getInventoryByFilm(Integer filmId) {
        return inventoryRepository.findByFilmFilmId(filmId);
    }

    /**
     * Get all inventory for a specific store
     */
    public List<Inventory> getInventoryByStore(Integer storeId) {
        return inventoryRepository.findByStoreStoreId(storeId);
    }

    /**
     * Get inventory for a specific film at a specific store
     */
    public List<Inventory> getInventoryByFilmAndStore(Integer filmId, Integer storeId) {
        return inventoryRepository.findByFilmFilmIdAndStoreStoreId(filmId, storeId);
    }

    /**
     * CRITICAL: Check if film is available at a store
     * This is used by the Rental module (Member 4)
     */
    public boolean isFilmAvailableAtStore(Integer filmId, Integer storeId) {
        Long availableCount = inventoryRepository.countAvailableByFilmAndStore(filmId, storeId);
        return availableCount > 0;
    }

    /**
     * Get available inventory count for a film at a store
     */
    public Long getAvailableInventoryCount(Integer filmId, Integer storeId) {
        return inventoryRepository.countAvailableByFilmAndStore(filmId, storeId);
    }

    /**
     * Get all available inventory for a film at a store
     */
    public List<Inventory> getAvailableInventory(Integer filmId, Integer storeId) {
        return inventoryRepository.findAvailableByFilmAndStore(filmId, storeId);
    }

    /**
     * CRITICAL: Get first available inventory item for rental
     * This is used by Rental Service to create a new rental
     */
    public Inventory getFirstAvailableInventory(Integer filmId, Integer storeId) {
        return inventoryRepository.findFirstAvailableByFilmAndStore(filmId, storeId)
                .orElseThrow(() -> new RuntimeException(
                        "No available inventory for film " + filmId + " at store " + storeId));
    }

    /**
     * Check if specific inventory item is available
     */
    public boolean isInventoryAvailable(Integer inventoryId) {
        return inventoryRepository.isInventoryAvailable(inventoryId);
    }

    /**
     * Get all currently rented inventory items
     */
    public List<Inventory> getCurrentlyRentedInventory() {
        return inventoryRepository.findAllCurrentlyRented();
    }

    /**
     * Count total inventory for a film
     */
    public Long countInventoryForFilm(Integer filmId) {
        return inventoryRepository.countByFilmFilmId(filmId);
    }

    /**
     * Create new inventory item
     */
    @Transactional
    public Inventory createInventory(Film film, Stores store) {
        if (film == null || store == null) {
            throw new RuntimeException("Film and Store cannot be null");
        }

        Inventory inventory = Inventory.builder()
                .film(film)
                .store(store)
                .build();

        return inventoryRepository.save(inventory);
    }

    /**
     * Create new inventory item by IDs
     */
    @Transactional
    public Inventory createInventory(Integer filmId, Integer storeId) {
        // Note: In real implementation, you'd inject FilmService and StoreService
        // to fetch the actual entities. For now, this is a placeholder.
        // You'll need to coordinate with Member 1 (Films) and Member 6 (Stores)

        Inventory inventory = new Inventory();
        // Set film and store using the IDs
        // This requires FilmService and StoreService

        return inventoryRepository.save(inventory);
    }

    /**
     * Delete inventory item (only if not currently rented)
     */
    @Transactional
    public void deleteInventory(Integer id) {
        Inventory inventory = getInventoryById(id);

        if (inventory.isCurrentlyRented()) {
            throw new RuntimeException(
                    "Cannot delete inventory. It is currently rented out.");
        }

        inventoryRepository.delete(inventory);
    }

    /**
     * INTEGRATION METHOD: For Film Service (Member 1)
     * Get total and available inventory count for display
     */
    public InventoryStatus getInventoryStatus(Integer filmId, Integer storeId) {
        Long total = inventoryRepository.countByFilmFilmId(filmId);
        Long available = inventoryRepository.countAvailableByFilmAndStore(filmId, storeId);

        return new InventoryStatus(total, available, available > 0);
    }

    /**
     * Inner class for inventory status
     */
    public static class InventoryStatus {
        private final Long totalCount;
        private final Long availableCount;
        private final boolean isAvailable;

        public InventoryStatus(Long totalCount, Long availableCount, boolean isAvailable) {
            this.totalCount = totalCount;
            this.availableCount = availableCount;
            this.isAvailable = isAvailable;
        }

        public Long getTotalCount() { return totalCount; }
        public Long getAvailableCount() { return availableCount; }
        public boolean isAvailable() { return isAvailable; }
    }
}