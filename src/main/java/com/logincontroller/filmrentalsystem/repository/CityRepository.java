package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<City> findByCity(String city);

    // ✅ Correct — navigates the @ManyToOne Country relationship
    List<City> findByCountryCountryId(int countryId);

    // ✅ Search cities by country name
    List<City> findByCountryCountryIgnoreCase(String countryName);
}