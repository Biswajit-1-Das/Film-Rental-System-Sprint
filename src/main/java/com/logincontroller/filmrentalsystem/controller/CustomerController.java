package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // GET /customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // GET /customers/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    // GET /customers/store/{storeId}
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Customer>> getCustomersByStore(
            @PathVariable int storeId) {
        return ResponseEntity.ok(customerService.getCustomersByStore(storeId));
    }

    // GET /customers/active
    @GetMapping("/active")
    public ResponseEntity<List<Customer>> getActiveCustomers() {
        return ResponseEntity.ok(customerService.getActiveCustomers());
    }

    // GET /customers/inactive
    @GetMapping("/inactive")
    public ResponseEntity<List<Customer>> getInactiveCustomers() {
        return ResponseEntity.ok(customerService.getInactiveCustomers());
    }

    // GET /customers/search?firstName=John
    // GET /customers/search?lastName=Smith
    // GET /customers/search?email=john@example.com
    @GetMapping("/search")
    public ResponseEntity<?> searchCustomers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {

        if (email != null) {
            return ResponseEntity.ok(customerService.getCustomerByEmail(email));
        }
        if (firstName != null) {
            return ResponseEntity.ok(
                    customerService.getCustomersByFirstName(firstName));
        }
        if (lastName != null) {
            return ResponseEntity.ok(
                    customerService.getCustomersByLastName(lastName));
        }
        return ResponseEntity.badRequest()
                .body("Provide at least one search param: firstName, lastName or email");
    }

    // GET /customers/address/{addressId}
    @GetMapping("/address/{addressId}")
    public ResponseEntity<List<Customer>> getCustomersByAddress(
            @PathVariable int addressId) {
        return ResponseEntity.ok(customerService.getCustomersByAddress(addressId));
    }

    // POST /customers
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    // PUT /customers/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable int id,
            @RequestBody Customer updatedData) {
        return ResponseEntity.ok(customerService.updateCustomer(id, updatedData));
    }

    // PATCH /customers/{id}/activate
    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateCustomer(@PathVariable int id) {
        customerService.activateCustomer(id);
        return ResponseEntity.ok("Customer " + id + " activated");
    }

    // PATCH /customers/{id}/deactivate
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateCustomer(@PathVariable int id) {
        customerService.deactivateCustomer(id);
        return ResponseEntity.ok("Customer " + id + " deactivated");
    }

    // DELETE /customers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer " + id + " deleted successfully");
    }
}