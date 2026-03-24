package com.logincontroller.filmrentalsystem.repository;


import com.logincontroller.filmrentalsystem.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    List<Country> findAllByOrderByCountryAsc();

    Optional<Country> findByCountryIgnoreCase(String country);

    boolean existsByCountryIgnoreCase(String country);
}