package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.CustomerResponseDTO;
import com.logincontroller.filmrentalsystem.dto.StaffResponseDTO;
import com.logincontroller.filmrentalsystem.dto.StoreResponseDTO;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.service.AddressService;
import com.logincontroller.filmrentalsystem.service.StaffService;
import com.logincontroller.filmrentalsystem.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://10.191.27.14:9090")
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final AddressService addressService;
    private final StaffService staffService;

    @PostMapping("/post")
    public ResponseEntity<StoreResponseDTO> createStore(@RequestBody Store store) {
        if (store.getAddress() != null && store.getAddress().getAddressId() != null) {
            store.setAddress(addressService.getEntityById(store.getAddress().getAddressId()));
        }
        if (store.getManagerStaff() != null && store.getManagerStaff().getStaffId() != null) {
            store.setManagerStaff(staffService.getEntityById(store.getManagerStaff().getStaffId()));
        }
        return ResponseEntity.ok(storeService.saveStore(store));
    }

    @PutMapping("/{storeId}/address/{addressId}")
    public ResponseEntity<StoreResponseDTO> linkAddress(
            @PathVariable Byte storeId,
            @PathVariable Short addressId) {
        return ResponseEntity.ok(storeService.linkStoreAddress(storeId, addressId));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<StoreResponseDTO>> byCity(@PathVariable String city) {
        return ResponseEntity.ok(storeService.getStoresByCity(city));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<StoreResponseDTO>> byCountry(@PathVariable String country) {
        return ResponseEntity.ok(storeService.getStoresByCountry(country));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<StoreResponseDTO>> byPhone(@PathVariable String phone) {
        return ResponseEntity.ok(storeService.getStoresByPhone(phone));
    }

    @PutMapping("/update/{storeId}/{phone}")
    public ResponseEntity<StoreResponseDTO> updatePhone(
            @PathVariable Byte storeId,
            @PathVariable String phone) {
        return ResponseEntity.ok(storeService.updateStorePhone(storeId, phone));
    }

    @PutMapping("/{storeId}/manager/{manager_staff_id}")
    public ResponseEntity<StoreResponseDTO> setManager(
            @PathVariable Byte storeId,
            @PathVariable Byte manager_staff_id) {
        return ResponseEntity.ok(storeService.setStoreManager(storeId, manager_staff_id));
    }

    @GetMapping("/staff/{storeId}")
    public ResponseEntity<List<StaffResponseDTO>> staffForStore(@PathVariable Byte storeId) {
        return ResponseEntity.ok(storeService.getStaffForStore(storeId));
    }

    @GetMapping("/customer/{storeId}")
    public ResponseEntity<List<CustomerResponseDTO>> customersForStore(@PathVariable Byte storeId) {
        return ResponseEntity.ok(storeService.getCustomersForStore(storeId));
    }

    @GetMapping("/manager/{storeId}")
    public ResponseEntity<StaffResponseDTO> managerForStore(@PathVariable Byte storeId) {
        return ResponseEntity.ok(storeService.getManagerForStore(storeId));
    }

    @GetMapping("/managers")
    public ResponseEntity<List<StaffResponseDTO>> allManagers() {
        return ResponseEntity.ok(storeService.getAllStoreManagers());
    }

    @GetMapping
    public ResponseEntity<List<StoreResponseDTO>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<List<StoreResponseDTO>> getStoresByAddress(@PathVariable Short addressId) {
        return ResponseEntity.ok(storeService.getStoresByAddress(addressId));
    }

    @GetMapping("/manager-staff/{staffId}")
    public ResponseEntity<List<StoreResponseDTO>> getStoresByManagerStaff(@PathVariable Byte staffId) {
        return ResponseEntity.ok(storeService.getStoresByManagerStaff(staffId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> getStoreById(@PathVariable Byte id) {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDTO> updateStore(@PathVariable Byte id,
                                                          @RequestBody Store store) {
        Store existing = storeService.getEntityById(id);
        if (store.getAddress() != null && store.getAddress().getAddressId() != null) {
            existing.setAddress(
                    addressService.getEntityById(store.getAddress().getAddressId())
            );
        }
        if (store.getManagerStaff() != null && store.getManagerStaff().getStaffId() != null) {
            existing.setManagerStaff(
                    staffService.getEntityById(store.getManagerStaff().getStaffId()));
        }
        return ResponseEntity.ok(storeService.saveStore(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Byte id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store deleted successfully");
    }
}
