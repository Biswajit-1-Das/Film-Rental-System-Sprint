package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Integer> {
    Optional<City> findByCity(String city);
    Optional<City> findByCountryId(int countryId);
}
