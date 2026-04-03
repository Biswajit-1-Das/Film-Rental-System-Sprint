package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.InventoryResponseDTO;
import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://10.191.27.14:9090")
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @PostMapping({ "", "/add" })
    public ResponseEntity<InventoryResponseDTO> add(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.saveInventory(inventory));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDTO>> getAll() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping({"/{id}", "/id/{id}"})
    public ResponseEntity<InventoryResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }


    @GetMapping("/films")
    public ResponseEntity<List<InventoryResponseDTO>> getAllFilms() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<InventoryResponseDTO>> getByStore(@PathVariable Byte id) {
        return ResponseEntity.ok(inventoryService.getInventoryByStore(id));
    }

    @GetMapping("/film/{id}")
    public ResponseEntity<List<InventoryResponseDTO>> getByFilm(@PathVariable Short id) {
        return ResponseEntity.ok(inventoryService.getInventoryByFilm(id));
    }

    @GetMapping("/film/{filmId}/store/{storeId}")
    public ResponseEntity<List<InventoryResponseDTO>> getByFilmAndStore(
            @PathVariable Short filmId,
            @PathVariable Byte storeId) {
        return ResponseEntity.ok(
                inventoryService.getInventoryByFilmAndStore(filmId, storeId)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            inventoryService.deleteInventory(id);
        } catch (Exception e) {
        }
        return ResponseEntity.noContent().build();
    }
}