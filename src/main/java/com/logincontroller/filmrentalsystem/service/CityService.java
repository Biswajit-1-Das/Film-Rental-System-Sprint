package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;
    public City saveCity(City city)
    {
        return city;
    }
    public List<City> getAllCities()
    {
        return cityRepository.findAll();
    }
    public City findById(int cityId)
    {
        return cityRepository.findById(cityId)
                .orElseThrow(()-> new RuntimeException("City not found"));
    }
    public City findByCity(String city)
    {
        return cityRepository.findByCity(city)
                .orElseThrow(()-> new RuntimeException("City not found"));
    }
    public City findByCountryId(int countryId)
    {
        return cityRepository.findByCountryId(countryId)
                .orElseThrow(()->new RuntimeException("City not found"));
    }
}
