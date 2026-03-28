package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

    // ✅ Derived Queries

    List<Store> findByAddressAddressId(Integer addressId);

    List<Store> findByManagerStaffStaffId(Integer staffId);
}