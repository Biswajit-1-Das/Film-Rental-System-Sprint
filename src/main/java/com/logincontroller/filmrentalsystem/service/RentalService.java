package com.logincontroller.filmrentalsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.logincontroller.filmrentalsystem.model.Rental;
import com.logincontroller.filmrentalsystem.repository.RentalRepository;

@Service
@RequiredArgsConstructor

public class RentalService {

    private final RentalRepository rentalRepository;

    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public Rental getById(int id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        rentalRepository.deleteById(id);
    }
}