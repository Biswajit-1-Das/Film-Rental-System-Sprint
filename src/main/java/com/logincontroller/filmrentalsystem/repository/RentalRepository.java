package com.logincontroller.filmrentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logincontroller.filmrentalsystem.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}