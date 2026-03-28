package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Byte> {

    List<Staff> findByAddressAddressId(Short addressId);

    List<Staff> findByStoreStoreId(Byte storeId);

    List<Staff> findByActive(Byte active);

    Staff findByUsername(String username);

    List<Staff> findByLastNameContainingIgnoreCase(String lastName);

    List<Staff> findByFirstNameContainingIgnoreCase(String firstName);

    Optional<Staff> findByEmailIgnoreCase(String email);

    List<Staff> findByAddress_City_City(String city);

    List<Staff> findByAddress_City_Country_Country(String country);

    List<Staff> findByAddress_Phone(String phone);
}
