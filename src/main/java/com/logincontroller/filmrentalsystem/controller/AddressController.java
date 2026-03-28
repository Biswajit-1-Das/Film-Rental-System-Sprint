package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Address>> getAddressesByCity(@PathVariable Integer cityId) {
        return ResponseEntity.ok(addressService.getAddressesByCity(cityId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Address>> searchByDistrict(@RequestParam String district) {
        return ResponseEntity.ok(addressService.searchByDistrict(district));
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.saveAddress(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Integer id,
                                                 @RequestBody Address address) {
        Address existing = addressService.getAddressById(id);
        existing.setAddress(address.getAddress());
        existing.setAddress2(address.getAddress2());
        existing.setDistrict(address.getDistrict());
        existing.setPostalCode(address.getPostalCode());
        existing.setPhone(address.getPhone());
        existing.setCity(address.getCity());
        existing.setLocation(address.getLocation());
        return ResponseEntity.ok(addressService.saveAddress(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Address deleted successfully");
    }
}