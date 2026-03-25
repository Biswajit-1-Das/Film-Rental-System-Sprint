package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
}