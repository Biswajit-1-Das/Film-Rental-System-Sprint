package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Integer> {

    // ✅ Find payments by Customer ID
    List<Payments> findByCustomerCustomerId(Integer customerId);

    // ✅ Find payments by Staff ID
    List<Payments> findByStaffStaffId(Integer staffId);

    // ✅ Find payments by Rental ID
    List<Payments> findByRentalRentalId(Integer rentalId);
}