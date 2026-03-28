package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.DateRevenueDTO;
import com.logincontroller.filmrentalsystem.dto.FilmRevenueDTO;
import com.logincontroller.filmrentalsystem.dto.PaymentsResponseDTO;
import com.logincontroller.filmrentalsystem.model.Payments;
import com.logincontroller.filmrentalsystem.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentsService paymentsService;

    @PostMapping("/add")
    public ResponseEntity<PaymentsResponseDTO> addPayment(
            @RequestParam Short customerId,
            @RequestParam Byte staffId,
            @RequestParam Integer rentalId,
            @RequestBody Payments payment) {
        return ResponseEntity.ok(paymentsService.createPayment(customerId, staffId, rentalId, payment));
    }

    @GetMapping("/revenue/datewise")
    public ResponseEntity<List<DateRevenueDTO>> revenueDatewise() {
        return ResponseEntity.ok(paymentsService.revenueByDate());
    }

    @GetMapping("/revenue/datewise/store/{id}")
    public ResponseEntity<List<DateRevenueDTO>> revenueDatewiseStore(@PathVariable Byte id) {
        return ResponseEntity.ok(paymentsService.revenueByDateForStore(id));
    }

    @GetMapping({"/revenue/filmwise", "/revenue/filmwise/"})
    public ResponseEntity<List<FilmRevenueDTO>> revenueFilmwise() {
        return ResponseEntity.ok(paymentsService.revenueByFilm());
    }

    @GetMapping("/revenue/film/{id}")
    public ResponseEntity<BigDecimal> revenueForFilm(@PathVariable Short id) {
        return ResponseEntity.ok(paymentsService.revenueTotalForFilm(id));
    }

    @GetMapping("/revenue/films/store/{id}")
    public ResponseEntity<List<FilmRevenueDTO>> revenueFilmsForStore(@PathVariable Byte id) {
        return ResponseEntity.ok(paymentsService.revenueByFilmsForStore(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentsResponseDTO> getById(@PathVariable Short id) {
        return ResponseEntity.ok(paymentsService.getPaymentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentsResponseDTO>> getAll() {
        return ResponseEntity.ok(paymentsService.getAllPayments());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<PaymentsResponseDTO>> byCustomer(@PathVariable Short customerId) {
        return ResponseEntity.ok(paymentsService.getPaymentsByCustomer(customerId));
    }

    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<PaymentsResponseDTO>> byStaff(@PathVariable Byte staffId) {
        return ResponseEntity.ok(paymentsService.getPaymentsByStaff(staffId));
    }

    @GetMapping("/rental/{rentalId}")
    public ResponseEntity<List<PaymentsResponseDTO>> byRental(@PathVariable Integer rentalId) {
        return ResponseEntity.ok(paymentsService.getPaymentsByRental(rentalId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentsResponseDTO> updatePayment(
            @PathVariable Short id,
            @RequestParam Short customerId,
            @RequestParam Byte staffId,
            @RequestParam Integer rentalId,
            @RequestBody Payments payment) {
        return ResponseEntity.ok(paymentsService.updatePayment(id, payment, customerId, staffId, rentalId));
    }
}
