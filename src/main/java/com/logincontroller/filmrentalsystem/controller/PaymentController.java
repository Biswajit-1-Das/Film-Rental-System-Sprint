package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Payments;
import com.logincontroller.filmrentalsystem.service.PaymentsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentsService paymentsService;

    public PaymentController(PaymentsService paymentsService) {
        this.paymentsService = paymentsService;
    }

    @GetMapping
    public List<Payments> getAllPayments() {
        return paymentsService.getAllPayments();
    }

    @GetMapping("/{id}")
    public Payments getPaymentById(@PathVariable Integer id) {
        return paymentsService.getPaymentById(id);
    }

    @PostMapping
    public Payments createPayment(@RequestBody Payments payment) {
        return paymentsService.createPayment(payment);
    }

    @PutMapping("/{id}")
    public Payments updatePayment(@PathVariable Integer id, @RequestBody Payments payment) {
        return paymentsService.updatePayment(id, payment);
    }

    @PatchMapping("/{id}")
    public Payments patchPayment(@PathVariable Integer id, @RequestBody Payments payment) {
        return paymentsService.patchPayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Integer id) {
        paymentsService.deletePayment(id);
        return "Payment deleted successfully!";
    }
}