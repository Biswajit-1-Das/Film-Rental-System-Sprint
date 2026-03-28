package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Country;
import com.logincontroller.filmrentalsystem.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    // GET /countries
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    // GET /countries/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable int id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    // GET /countries/search?name=India
    @GetMapping("/search")
    public ResponseEntity<Country> getCountryByName(@RequestParam String name) {
        return ResponseEntity.ok(countryService.getCountryByName(name));
    }

    // POST /countries
    @PostMapping
    public ResponseEntity<Country> createCountry(@RequestParam String name) {
        return ResponseEntity.ok(countryService.createCountry(name));
    }

    // POST /countries/resolve
    // Returns existing country if found, creates new one if not
    @PostMapping("/resolve")
    public ResponseEntity<Country> resolveOrCreate(@RequestParam String name) {
        return ResponseEntity.ok(countryService.resolveOrCreate(name));
    }

    // PUT /countries/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(
            @PathVariable int id,
            @RequestParam String name) {
        return ResponseEntity.ok(countryService.updateCountry(id, name));
    }

    // DELETE /countries/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable int id) {
        countryService.deleteCountry(id);
        return ResponseEntity.ok("Country deleted successfully");
    }
}