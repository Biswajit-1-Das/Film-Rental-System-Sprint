package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.dto.RentalResponseDTO;
import com.logincontroller.filmrentalsystem.model.Rental;
import com.logincontroller.filmrentalsystem.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("/add")
    public ResponseEntity<RentalResponseDTO> addRental(
            @RequestParam Short customerId,
            @RequestParam Byte staffId,
            @RequestParam Integer inventoryId,
            @RequestBody(required = false) Rental rental) {
        Rental body = rental != null ? rental : Rental.builder().build();
        return ResponseEntity.ok(rentalService.createRental(customerId, staffId, inventoryId, body));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<RentalResponseDTO>> byCustomer(@PathVariable Short id) {
        return ResponseEntity.ok(rentalService.getRentalsByCustomer(id));
    }

    @GetMapping("/toptenfilms")
    public ResponseEntity<List<FilmResponseDTO>> topTenFilms() {
        return ResponseEntity.ok(rentalService.getTopTenFilmsByRentalCount());
    }

    @GetMapping("/toptenfilms/store/{id}")
    public ResponseEntity<List<FilmResponseDTO>> topTenFilmsForStore(@PathVariable Byte id) {
        return ResponseEntity.ok(rentalService.getTopTenFilmsByRentalCountForStore(id));
    }

    @GetMapping("/due/store/{id}")
    public ResponseEntity<List<RentalResponseDTO>> dueByStore(@PathVariable Byte id) {
        return ResponseEntity.ok(rentalService.getDueRentalsByStore(id));
    }

    @PutMapping("/update/returndate/{id}")
    public ResponseEntity<RentalResponseDTO> updateReturnDate(
            @PathVariable Integer id,
            @RequestParam LocalDateTime returnDate) {
        return ResponseEntity.ok(rentalService.updateReturnDate(id, returnDate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @GetMapping
    public ResponseEntity<List<RentalResponseDTO>> getAll() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> updateRental(
            @PathVariable Integer id,
            @RequestParam Short customerId,
            @RequestParam Byte staffId,
            @RequestParam Integer inventoryId,
            @RequestBody Rental rental) {
        return ResponseEntity.ok(rentalService.updateRental(id, rental, customerId, staffId, inventoryId));
    }
}
