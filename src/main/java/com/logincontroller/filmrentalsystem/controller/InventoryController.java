package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * GET /api/inventory
     * Get all inventory items
     */
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventory = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventory);
    }

    /**
     * GET /api/inventory/{id}
     * Get inventory by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Integer id) {
        Inventory inventory = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    /**
     * GET /api/inventory/{id}/details
     * Get inventory with film and store details
     */
    @GetMapping("/{id}/details")
    public ResponseEntity<Inventory> getInventoryWithDetails(@PathVariable Integer id) {
        Inventory inventory = inventoryService.getInventoryWithDetails(id);
        return ResponseEntity.ok(inventory);
    }

    /**
     * GET /api/inventory/film/{filmId}
     * Get all inventory for a specific film
     */
    @GetMapping("/film/{filmId}")
    public ResponseEntity<List<Inventory>> getInventoryByFilm(@PathVariable Integer filmId) {
        List<Inventory> inventory = inventoryService.getInventoryByFilm(filmId);
        return ResponseEntity.ok(inventory);
    }

    /**
     * GET /api/inventory/store/{storeId}
     * Get all inventory for a specific store
     */
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Inventory>> getInventoryByStore(@PathVariable Integer storeId) {
        List<Inventory> inventory = inventoryService.getInventoryByStore(storeId);
        return ResponseEntity.ok(inventory);
    }

    /**
     * GET /api/inventory/available
     * Get available inventory for a film at a store
     * Example: /api/inventory/available?filmId=1&storeId=1
     */
    @GetMapping("/available")
    public ResponseEntity<List<Inventory>> getAvailableInventory(
            @RequestParam Integer filmId,
            @RequestParam Integer storeId) {
        List<Inventory> inventory = inventoryService.getAvailableInventory(filmId, storeId);
        return ResponseEntity.ok(inventory);
    }

    /**
     * GET /api/inventory/check-availability
     * Check if film is available at a store
     * Example: /api/inventory/check-availability?filmId=1&storeId=1
     *
     * IMPORTANT: This is used by Member 4 (Rental Service)
     */
    @GetMapping("/check-availability")
    public ResponseEntity<AvailabilityResponse> checkAvailability(
            @RequestParam Integer filmId,
            @RequestParam Integer storeId) {

        boolean isAvailable = inventoryService.isFilmAvailableAtStore(filmId, storeId);
        Long availableCount = inventoryService.getAvailableInventoryCount(filmId, storeId);
        Long totalCount = inventoryService.countInventoryForFilm(filmId);

        AvailabilityResponse response = new AvailabilityResponse(
                isAvailable, availableCount, totalCount
        );

        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/inventory/status
     * Get inventory status for a film at a store
     * Example: /api/inventory/status?filmId=1&storeId=1
     */
    @GetMapping("/status")
    public ResponseEntity<InventoryService.InventoryStatus> getInventoryStatus(
            @RequestParam Integer filmId,
            @RequestParam Integer storeId) {

        InventoryService.InventoryStatus status =
                inventoryService.getInventoryStatus(filmId, storeId);

        return ResponseEntity.ok(status);
    }

    /**
     * GET /api/inventory/rented
     * Get all currently rented inventory items
     */
    @GetMapping("/rented")
    public ResponseEntity<List<Inventory>> getCurrentlyRented() {
        List<Inventory> inventory = inventoryService.getCurrentlyRentedInventory();
        return ResponseEntity.ok(inventory);
    }

    /**
     * Response DTO for availability check
     */
    public static class AvailabilityResponse {
        private final boolean available;
        private final Long availableCount;
        private final Long totalCount;

        public AvailabilityResponse(boolean available, Long availableCount, Long totalCount) {
            this.available = available;
            this.availableCount = availableCount;
            this.totalCount = totalCount;
        }

        public boolean isAvailable() { return available; }
        public Long getAvailableCount() { return availableCount; }
        public Long getTotalCount() { return totalCount; }
    }
}