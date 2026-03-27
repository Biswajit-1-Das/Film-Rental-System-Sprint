package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByStoreStoreId(int storeId);

    List<Customer> findByActive(int active);

    List<Customer> findByFirstNameIgnoreCase(String firstName);

    List<Customer> findByLastNameIgnoreCase(String lastName);

    Optional<Customer> findByEmailIgnoreCase(String email);

    List<Customer> findByAddressAddressId(int addressId);
}