package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Short> {

    List<Customer> findByStoreStoreId(Byte storeId);

    List<Customer> findByActive(Byte active);

    List<Customer> findByLastNameContainingIgnoreCase(String lastName);

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    List<Customer> findByEmailContainingIgnoreCase(String email);

    List<Customer> findByLastNameContainingIgnoreCase(String lastName);

    List<Customer> findByFirstNameContainingIgnoreCase(String firstName);

    List<Customer> findByEmailContainingIgnoreCase(String email);

    Optional<Customer> findByEmailIgnoreCase(String email);

    List<Customer> findByAddressAddressId(Short addressId);

    List<Customer> findByAddress_City_City(String city);

    List<Customer> findByAddress_City_Country_Country(String country);

    List<Customer> findByAddress_Phone(String phone);

    @Query("SELECT c FROM Customer c WHERE c.customerId = :cid AND c.address.addressId = :aid")
    Optional<Customer> findByCustomerIdAndAddressAddressId(@Param("cid") Short cid, @Param("aid") Short aid);
}
