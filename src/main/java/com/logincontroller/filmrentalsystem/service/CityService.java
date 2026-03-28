package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;  // ✅ removed unused CountryRepository

    // ── CREATE ────────────────────────────────────────────────
    @Transactional
    public City createCity(City city) {
        return cityRepository.save(city);
    }

    // ── READ ──────────────────────────────────────────────────
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(int cityId) {
        return cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException(
                        "City not found with id: " + cityId));
    }

    public City getCityByName(String cityName) {
        return cityRepository.findByCity(cityName)
                .orElseThrow(() -> new RuntimeException(
                        "City not found: " + cityName));
    }

    public List<City> getCitiesByCountryId(int countryId) {
        return cityRepository.findByCountryCountryId(countryId);
    }

    public List<City> getCitiesByCountryName(String countryName) {
        return cityRepository.findByCountryCountryIgnoreCase(countryName);
    }

    // ── UPDATE ────────────────────────────────────────────────
    @Transactional
    public City updateCity(int cityId, City updatedData) {
        City existing = getCityById(cityId);
        existing.setCity(updatedData.getCity());
        if (updatedData.getCountry() != null) {
            existing.setCountry(updatedData.getCountry());
        }
        return cityRepository.save(existing);
    }

    // ── DELETE ────────────────────────────────────────────────
    @Transactional
    public void deleteCity(int cityId) {
        if (!cityRepository.existsById(cityId)) {
            throw new RuntimeException("City not found with id: " + cityId);
        }
        cityRepository.deleteById(cityId);
    }
}