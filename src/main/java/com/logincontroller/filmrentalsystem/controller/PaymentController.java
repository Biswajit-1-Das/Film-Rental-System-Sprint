package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Payments;
import com.logincontroller.filmrentalsystem.service.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentsService paymentsService;

    // ✅ CREATE
    @PostMapping
    public Payments createPayment(@RequestParam Integer customerId,
                                  @RequestParam Integer staffId,
                                  @RequestParam Integer rentalId,
                                  @RequestBody Payments payment) {

        return paymentsService.createPayment(customerId, staffId, rentalId, payment);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public Payments getPaymentById(@PathVariable Integer id) {
        return paymentsService.getPaymentById(id);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Payments> getAllPayments() {
        return paymentsService.getAllPayments();
    }

    // ✅ GET BY CUSTOMER
    @GetMapping("/customer/{customerId}")
    public List<Payments> getByCustomer(@PathVariable Integer customerId) {
        return paymentsService.getPaymentsByCustomer(customerId);
    }

    // ✅ GET BY STAFF
    @GetMapping("/staff/{staffId}")
    public List<Payments> getByStaff(@PathVariable Integer staffId) {
        return paymentsService.getPaymentsByStaff(staffId);
    }

    // ✅ GET BY RENTAL
    @GetMapping("/rental/{rentalId}")
    public List<Payments> getByRental(@PathVariable Integer rentalId) {
        return paymentsService.getPaymentsByRental(rentalId);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Payments updatePayment(@PathVariable Integer id,
                                  @RequestParam Integer customerId,
                                  @RequestParam Integer staffId,
                                  @RequestParam Integer rentalId,
                                  @RequestBody Payments payment) {

        return paymentsService.updatePayment(id, payment, customerId, staffId, rentalId);
    }
}