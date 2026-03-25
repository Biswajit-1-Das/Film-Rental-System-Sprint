package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    Optional<Address> findByDistrict(String district);
    Optional<Address> findByPostalCode(String postalCode);
    Optional<Address> findByPhone(String postalCode);
    Optional<Address> findByCity(City city);
}
