package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.CountryDTO;
import com.logincontroller.filmrentalsystem.model.Country;
import com.logincontroller.filmrentalsystem.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/search")
    public ResponseEntity<CountryDTO> getCountryByName(@RequestParam String name) {
        return ResponseEntity.ok(countryService.getCountryByName(name));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> countryExists(@RequestParam String name) {
        return ResponseEntity.ok(countryService.countryExistsIgnoreCase(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Short id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PostMapping
    public ResponseEntity<CountryDTO> createCountry(@RequestBody Country country) {
        return ResponseEntity.ok(countryService.saveCountry(country));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDTO> updateCountry(@PathVariable Short id,
                                                    @RequestBody Country country) {
        Country existing = countryService.getEntityById(id);
        existing.setCountry(country.getCountry());
        return ResponseEntity.ok(countryService.saveCountry(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable Short id) {
        countryService.deleteCountry(id);
        return ResponseEntity.ok("Country deleted successfully");
    }
}
