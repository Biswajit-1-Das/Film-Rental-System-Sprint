package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.StoreDTO;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.service.AddressService;
import com.logincontroller.filmrentalsystem.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Integer id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@RequestBody Store store) {
        if (store.getAddress() != null && store.getAddress().getAddressId() != null) {
            store.setAddress(addressService.getEntityById(store.getAddress().getAddressId()));
        }
        return ResponseEntity.ok(storeService.saveStore(store));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Integer id,
                                                @RequestBody Store store) {
        Store existing = storeService.getEntityById(id);
        if (store.getAddress() != null && store.getAddress().getAddressId() != null) {
            existing.setAddress(
                    addressService.getEntityById(store.getAddress().getAddressId())
            );
        }
        return ResponseEntity.ok(storeService.saveStore(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store deleted successfully");
    }
}