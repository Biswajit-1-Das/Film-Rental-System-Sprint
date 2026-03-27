package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

    // 🔍 Custom Queries (optional but useful)

    List<Payments> findByCustomerCustomerId(Integer customerId);

    List<Payments> findByStaffStaffId(Integer staffId);

    List<Payments> findByRentalRentalId(Integer rentalId);
}