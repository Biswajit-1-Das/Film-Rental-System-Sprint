package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    // ✅ Derived Queries (aligned with entity relationships)

    List<Rental> findByCustomerCustomerId(Integer customerId);

    List<Rental> findByStaffStaffId(Integer staffId);

    List<Rental> findByInventoryInventoryId(Integer inventoryId);
}