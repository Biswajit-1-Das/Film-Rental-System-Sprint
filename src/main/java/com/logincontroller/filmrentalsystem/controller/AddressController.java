package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.AddressDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.service.AddressService;
import com.logincontroller.filmrentalsystem.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AddressDTO>> getAddressesByCity(
            @PathVariable Integer cityId) {
        return ResponseEntity.ok(addressService.getAddressesByCity(cityId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AddressDTO>> searchByDistrict(
            @RequestParam String district) {
        return ResponseEntity.ok(addressService.searchByDistrict(district));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody Address address) {
        // resolve city entity from the cityId inside the address object
        if (address.getCity() != null && address.getCity().getCityId() != null) {
            address.setCity(cityService.getEntityById(address.getCity().getCityId()));
        }
        return ResponseEntity.ok(addressService.saveAddress(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Integer id,
                                                    @RequestBody Address address) {
        Address existing = addressService.getEntityById(id);
        existing.setAddress(address.getAddress());
        existing.setAddress2(address.getAddress2());
        existing.setDistrict(address.getDistrict());
        existing.setPostalCode(address.getPostalCode());
        existing.setPhone(address.getPhone());
        existing.setLocation(address.getLocation());
        if (address.getCity() != null && address.getCity().getCityId() != null) {
            existing.setCity(cityService.getEntityById(address.getCity().getCityId()));
        }
        return ResponseEntity.ok(addressService.saveAddress(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Address deleted successfully");
    }
}