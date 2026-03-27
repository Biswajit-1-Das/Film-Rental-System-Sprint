package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.*;
import com.logincontroller.filmrentalsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    // ✅ CREATE
    public Rental createRental(Integer customerId, Integer staffId,
                               Integer inventoryId, Rental rental) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setInventory(inventory);

        rental.setRentalDate(LocalDateTime.now());
        rental.setLastUpdate(LocalDateTime.now());

        return rentalRepository.save(rental);
    }

    // ✅ GET BY ID
    public Rental getRentalById(Integer id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));
    }

    // ✅ GET ALL
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // ✅ GET BY CUSTOMER
    public List<Rental> getRentalsByCustomer(Integer customerId) {
        return rentalRepository.findByCustomerCustomerId(customerId);
    }

    // ✅ GET BY STAFF
    public List<Rental> getRentalsByStaff(Integer staffId) {
        return rentalRepository.findByStaffStaffId(staffId);
    }

    // ✅ GET BY INVENTORY
    public List<Rental> getRentalsByInventory(Integer inventoryId) {
        return rentalRepository.findByInventoryInventoryId(inventoryId);
    }

    // ✅ UPDATE
    public Rental updateRental(Integer id, Rental updatedRental,
                               Integer customerId, Integer staffId, Integer inventoryId) {

        Rental existing = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        existing.setCustomer(customer);
        existing.setStaff(staff);
        existing.setInventory(inventory);
        existing.setRentalDate(updatedRental.getRentalDate());
        existing.setReturnDate(updatedRental.getReturnDate());
        existing.setLastUpdate(LocalDateTime.now());

        return rentalRepository.save(existing);
    }
}