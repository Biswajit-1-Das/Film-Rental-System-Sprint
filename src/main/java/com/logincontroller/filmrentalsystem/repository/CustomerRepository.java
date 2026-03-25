package com.logincontroller.filmrentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.logincontroller.filmrentalsystem.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}