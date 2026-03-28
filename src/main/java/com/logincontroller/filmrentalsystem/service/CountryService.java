package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Country;
import com.logincontroller.filmrentalsystem.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    // ── CREATE ────────────────────────────────────────────────
    @Transactional
    public Country createCountry(String countryName) {
        if (countryRepository.existsByCountryIgnoreCase(countryName)) {
            throw new RuntimeException(
                    "Country already exists: " + countryName);
        }
        return countryRepository.save(
                Country.builder().country(countryName).build());
    }

    // ── READ ──────────────────────────────────────────────────
    public List<Country> getAllCountries() {
        return countryRepository.findAllByOrderByCountryAsc();
    }

    public Country getCountryById(int id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Country not found with id: " + id));
    }

    public Country getCountryByName(String name) {
        return countryRepository.findByCountryIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException(
                        "Country not found: " + name));
    }

    @Transactional
    public Country resolveOrCreate(String countryName) {
        return countryRepository.findByCountryIgnoreCase(countryName)
                .orElseGet(() -> countryRepository.save(
                        Country.builder().country(countryName).build()));
    }

    // ── UPDATE ────────────────────────────────────────────────
    @Transactional
    public Country updateCountry(int id, String newName) {
        Country existing = getCountryById(id);
        existing.setCountry(newName);
        return countryRepository.save(existing);
    }

    // ── DELETE ────────────────────────────────────────────────
    @Transactional
    public void deleteCountry(int id) {
        if (!countryRepository.existsById(id)) {
            throw new RuntimeException(
                    "Country not found with id: " + id);
        }
        countryRepository.deleteById(id);
    }
}