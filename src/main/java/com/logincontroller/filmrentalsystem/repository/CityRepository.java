package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Short> {

    Optional<City> findByCity(String city);

    List<City> findByCountryCountryId(Short countryId);

    List<City> findByCountry_CountryIgnoreCase(String countryName);
}
