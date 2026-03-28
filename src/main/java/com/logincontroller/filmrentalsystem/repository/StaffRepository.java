package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    // ✅ Derived Queries

    List<Staff> findByAddressAddressId(Integer addressId);

    List<Staff> findByStoreStoreId(Integer storeId);

    Staff findByUsername(String username);

}