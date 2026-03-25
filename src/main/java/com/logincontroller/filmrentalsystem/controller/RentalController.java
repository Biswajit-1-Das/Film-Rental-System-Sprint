package com.logincontroller.filmrentalsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.logincontroller.filmrentalsystem.model.Rental;
import com.logincontroller.filmrentalsystem.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor

public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public Rental create(@RequestBody Rental rental) {
        return rentalService.save(rental);
    }

    @GetMapping
    public List<Rental> getAll() {
        return rentalService.getAll();
    }

    @GetMapping("/{id}")
    public Rental getById(@PathVariable int id) {
        return rentalService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        rentalService.delete(id);
        return "Rental deleted successfully";
    }
}