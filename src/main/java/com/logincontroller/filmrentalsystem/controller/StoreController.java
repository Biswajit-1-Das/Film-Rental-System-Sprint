package com.logincontroller.filmrentalsystem.controller;


import org.springframework.web.bind.annotation.*;

import com.logincontroller.filmrentalsystem.model.Stores;
import com.logincontroller.filmrentalsystem.service.StoresService;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoresService storesService;

    public StoreController(StoresService storesService) {
        this.storesService = storesService;
    }

    @GetMapping
    public List<Stores> getAllStores() {
        return storesService.getAllStores();
    }

    @GetMapping("/{id}")
    public Stores getStoreById(@PathVariable Integer id) {
        return storesService.getStoreById(id);
    }

    @PostMapping
    public Stores createStore(@RequestBody Stores store) {
        return storesService.createStore(store);
    }

    @PutMapping("/{id}")
    public Stores updateStore(@PathVariable Integer id, @RequestBody Stores store) {
        return storesService.updateStore(id, store);
    }

    @PatchMapping("/{id}")
    public Stores patchStore(@PathVariable Integer id, @RequestBody Stores store) {
        return storesService.patchStore(id, store);
    }

    @DeleteMapping("/{id}")
    public String deleteStore(@PathVariable Integer id) {
        storesService.deleteStore(id);
        return "Store deleted successfully!";
    }
}