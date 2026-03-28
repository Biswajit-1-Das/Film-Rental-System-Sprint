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

    @GetMapping("/search")
    public ResponseEntity<List<AddressDTO>> searchByDistrict(@RequestParam String district) {
        return ResponseEntity.ok(addressService.searchByDistrict(district));
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AddressDTO>> getAddressesByCity(@PathVariable Short cityId) {
        return ResponseEntity.ok(addressService.getAddressesByCity(cityId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Short id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody Address address) {
        if (address.getCity() != null && address.getCity().getCityId() != null) {
            address.setCity(cityService.getEntityById(address.getCity().getCityId()));
        }
        return ResponseEntity.ok(addressService.saveAddress(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Short id,
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
    public ResponseEntity<String> deleteAddress(@PathVariable Short id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
