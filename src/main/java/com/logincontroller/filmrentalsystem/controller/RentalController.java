package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Rental;
import com.logincontroller.filmrentalsystem.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class
RentalController {

    @Autowired
    private RentalService rentalService;

    // ✅ CREATE
    @PostMapping
    public Rental createRental(@RequestParam Integer customerId,
                               @RequestParam Integer staffId,
                               @RequestParam Integer inventoryId,
                               @RequestBody Rental rental) {

        return rentalService.createRental(customerId, staffId, inventoryId, rental);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    // ✅ GET BY CUSTOMER
    @GetMapping("/customer/{customerId}")
    public List<Rental> getByCustomer(@PathVariable Integer customerId) {
        return rentalService.getRentalsByCustomer(customerId);
    }

    // ✅ GET BY STAFF
    @GetMapping("/staff/{staffId}")
    public List<Rental> getByStaff(@PathVariable Integer staffId) {
        return rentalService.getRentalsByStaff(staffId);
    }

    // ✅ GET BY INVENTORY
    @GetMapping("/inventory/{inventoryId}")
    public List<Rental> getByInventory(@PathVariable Integer inventoryId) {
        return rentalService.getRentalsByInventory(inventoryId);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable Integer id,
                               @RequestParam Integer customerId,
                               @RequestParam Integer staffId,
                               @RequestParam Integer inventoryId,
                               @RequestBody Rental rental) {

        return rentalService.updateRental(id, rental, customerId, staffId, inventoryId);
    }
}