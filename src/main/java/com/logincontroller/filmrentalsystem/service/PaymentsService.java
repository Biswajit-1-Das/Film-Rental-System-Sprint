package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.*;
import com.logincontroller.filmrentalsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentsService {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RentalRepository rentalRepository;

    // ✅ CREATE
    public Payments createPayment(Integer customerId, Integer staffId,
                                  Integer rentalId, Payments payment) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setRental(rental);

        payment.setPaymentDate(LocalDateTime.now());
        payment.setLastUpdate(LocalDateTime.now());

        return paymentsRepository.save(payment);
    }

    // ✅ GET BY ID
    public Payments getPaymentById(Integer id) {
        return paymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    // ✅ GET ALL
    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }

    // ✅ GET BY CUSTOMER ID (Aligned with repo)
    public List<Payments> getPaymentsByCustomer(Integer customerId) {
        return paymentsRepository.findByCustomerCustomerId(customerId);
    }

    // ✅ GET BY STAFF ID
    public List<Payments> getPaymentsByStaff(Integer staffId) {
        return paymentsRepository.findByStaffStaffId(staffId);
    }

    // ✅ GET BY RENTAL ID
    public List<Payments> getPaymentsByRental(Integer rentalId) {
        return paymentsRepository.findByRentalRentalId(rentalId);
    }

    // ✅ UPDATE
    public Payments updatePayment(Integer id, Payments updatedPayment,
                                  Integer customerId, Integer staffId, Integer rentalId) {

        Payments existing = paymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        existing.setAmount(updatedPayment.getAmount());
        existing.setCustomer(customer);
        existing.setStaff(staff);
        existing.setRental(rental);
        existing.setPaymentDate(updatedPayment.getPaymentDate());
        existing.setLastUpdate(LocalDateTime.now());

        return paymentsRepository.save(existing);
    }
}