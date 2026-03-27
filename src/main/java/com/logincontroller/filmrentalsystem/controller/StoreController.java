package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.service.StoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoresService storeService;

    // ✅ CREATE
    @PostMapping
    public Store createStore(@RequestParam Integer managerStaffId,
                             @RequestParam Integer addressId,
                             @RequestBody Store store) {

        return storeService.createStore(managerStaffId, addressId, store);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Integer id) {
        return storeService.getStoreById(id);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    // ✅ GET BY ADDRESS
    @GetMapping("/address/{addressId}")
    public List<Store> getByAddress(@PathVariable Integer addressId) {
        return storeService.getStoresByAddress(addressId);
    }

    // ✅ GET BY MANAGER
    @GetMapping("/manager/{staffId}")
    public List<Store> getByManager(@PathVariable Integer staffId) {
        return storeService.getStoresByManager(staffId);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Integer id,
                             @RequestParam Integer managerStaffId,
                             @RequestParam Integer addressId,
                             @RequestBody Store store) {

        return storeService.updateStore(id, store, managerStaffId, addressId);
    }
}