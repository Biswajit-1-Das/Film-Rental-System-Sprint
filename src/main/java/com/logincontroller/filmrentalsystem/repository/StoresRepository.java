package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Stores;
import com.logincontroller.filmrentalsystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoresRepository extends JpaRepository<Stores, Integer> {

    // Get store by manager
    Optional<Stores> findByManagerStaff(Staff manager);

    // Get stores by manager staff id
    Optional<Stores> findByManagerStaffStaffId(Integer staffId);

    // Get all active stores (if active column exists)
    List<Stores> findByActiveTrue();

    // Get store by address (optional)
    List<Stores> findByAddressAddressId(Integer addressId);

}