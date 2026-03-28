package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Byte> {

    List<Store> findByAddressAddressId(Short addressId);

    List<Store> findByManagerStaffStaffId(Byte staffId);

    List<Store> findByAddress_City_City(String city);

    List<Store> findByAddress_City_Country_Country(String country);

    List<Store> findByAddress_Phone(String phone);

    @Query("SELECT DISTINCT s.managerStaff FROM Store s WHERE s.managerStaff IS NOT NULL")
    List<Staff> findDistinctStoreManagers();
}
