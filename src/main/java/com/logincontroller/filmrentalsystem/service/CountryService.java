package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.CountryDTO;
import com.logincontroller.filmrentalsystem.model.Country;
import com.logincontroller.filmrentalsystem.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    private CountryDTO toDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setCountryId(country.getCountryId());
        dto.setCountry(country.getCountry());
        dto.setLastUpdate(country.getLastUpdate());
        return dto;
    }

    public Country getEntityById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
    }

    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAllByOrderByCountryAsc()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CountryDTO getCountryById(Integer id) {
        return toDTO(getEntityById(id));
    }

    public CountryDTO getCountryByName(String name) {
        Country country = countryRepository.findByCountryIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Country not found: " + name));
        return toDTO(country);
    }

    @Transactional
    public CountryDTO saveCountry(Country country) {
        return toDTO(countryRepository.save(country));
    }

    @Transactional
    public CountryDTO resolveOrCreate(String countryName) {
        Country country = countryRepository.findByCountryIgnoreCase(countryName)
                .orElseGet(() -> {
                    Country newCountry = new Country();
                    newCountry.setCountry(countryName);
                    return countryRepository.save(newCountry);
                });
        return toDTO(country);
    }

    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id);
    }
}