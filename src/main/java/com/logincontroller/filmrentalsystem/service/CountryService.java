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

    // Returns all countries sorted A-Z — used to populate address form dropdowns
    public List<Country> getAllCountries() {
        return countryRepository.findAllByOrderByCountryAsc();
    }

    // Fetch a single country by ID — throws if not found
    public Country getCountryById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Country not found with id: " + id));
    }

    // Find by name (case-insensitive) — used during address resolution
    public Country getCountryByName(String name) {
        return countryRepository.findByCountryIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException(
                        "Country not found: " + name));
    }

    // Returns existing country if name matches, otherwise creates a new one
    @Transactional
    public Country resolveOrCreate(String countryName) {
        return countryRepository.findByCountryIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = Country.builder()
                            .country(countryName)
                            .build();
                    return countryRepository.save(newCountry);
                });
    }
}
