package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.CustomerResponseDTO;
import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://10.191.27.14:9090")
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/post")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<CustomerResponseDTO>> byLastName(@PathVariable String ln) {
        return ResponseEntity.ok(customerService.searchByLastName(ln));
    }

    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<CustomerResponseDTO>> byFirstName(@PathVariable String fn) {
        return ResponseEntity.ok(customerService.getCustomersByFirstName(fn));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CustomerResponseDTO> byEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @GetMapping("/{id}/{addressId}")
    public ResponseEntity<CustomerResponseDTO> byIdAndAddress(
            @PathVariable Short id,
            @PathVariable Short addressId) {
        return ResponseEntity.ok(customerService.getCustomerByIdAndAddressId(id, addressId));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<CustomerResponseDTO>> byCity(@PathVariable String city) {
        return ResponseEntity.ok(customerService.getCustomersByCity(city));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<CustomerResponseDTO>> byCountry(@PathVariable String country) {
        return ResponseEntity.ok(customerService.getCustomersByCountry(country));
    }

    @GetMapping("/active")
    public ResponseEntity<List<CustomerResponseDTO>> active() {
        return ResponseEntity.ok(customerService.getActiveCustomers());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<CustomerResponseDTO>> inactive() {
        return ResponseEntity.ok(customerService.getInactiveCustomers());
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<CustomerResponseDTO>> byPhone(@PathVariable String phone) {
        return ResponseEntity.ok(customerService.getCustomersByPhone(phone));
    }

    @PutMapping("/update/{id}/firstname/{fn}")
    public ResponseEntity<CustomerResponseDTO> updateFirstName(
            @PathVariable Short id,
            @PathVariable String fn) {
        return ResponseEntity.ok(customerService.updateFirstName(id, fn));
    }

    @PutMapping("/update/{id}/lastname/{ln}")
    public ResponseEntity<CustomerResponseDTO> updateLastName(
            @PathVariable Short id,
            @PathVariable String ln) {
        return ResponseEntity.ok(customerService.updateLastName(id, ln));
    }

    @PutMapping("/update/{id}/email")
    public ResponseEntity<CustomerResponseDTO> updateEmail(
            @PathVariable Short id,
            @RequestParam String email) {
        return ResponseEntity.ok(customerService.updateEmail(id, email));
    }

    @PutMapping("/update/{id}/store")
    public ResponseEntity<CustomerResponseDTO> updateStore(
            @PathVariable Short id,
            @RequestParam Byte storeId) {
        return ResponseEntity.ok(customerService.updateStore(id, storeId));
    }

    @PutMapping("/update/{id}/phone")
    public ResponseEntity<CustomerResponseDTO> updatePhone(
            @PathVariable Short id,
            @RequestParam String phone) {
        return ResponseEntity.ok(customerService.updatePhone(id, phone));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomersByStore(@PathVariable Byte storeId) {
        return ResponseEntity.ok(customerService.getCustomersByStore(storeId));
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomersByAddress(@PathVariable Short addressId) {
        return ResponseEntity.ok(customerService.getCustomersByAddress(addressId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Short id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Short id,
            @RequestBody Customer updatedData) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedData));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateCustomer(@PathVariable Short id) {
        customerService.activateCustomer(id);
        return ResponseEntity.ok("Customer " + id + " activated");
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateCustomer(@PathVariable Short id) {
        customerService.deactivateCustomer(id);
        return ResponseEntity.ok("Customer " + id + " deactivated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Short id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer " + id + " deleted successfully");
    }
}
