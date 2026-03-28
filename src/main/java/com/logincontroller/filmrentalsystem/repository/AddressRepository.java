package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Short> {

    List<Address> findByCityCityId(Short cityId);

    List<Address> findByDistrictContainingIgnoreCase(String district);
}
