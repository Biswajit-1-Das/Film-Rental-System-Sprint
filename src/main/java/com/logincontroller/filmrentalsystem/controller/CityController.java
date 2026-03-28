package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.CityDTO;
import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.service.CityService;
import com.logincontroller.filmrentalsystem.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Integer id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<CityDTO>> getCitiesByCountry(
            @PathVariable Integer countryId) {
        return ResponseEntity.ok(cityService.getCitiesByCountry(countryId));
    }

    @PostMapping
    public ResponseEntity<CityDTO> createCity(@PathVariable Integer countryId,
                                              @RequestBody City city) {
        // resolve country entity and assign before saving
        city.setCountry(countryService.getEntityById(countryId));
        return ResponseEntity.ok(cityService.saveCity(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Integer id,
                                              @RequestBody City city) {
        City existing = cityService.getEntityById(id);
        existing.setCity(city.getCity());
        if (city.getCountry() != null) {
            existing.setCountry(
                    countryService.getEntityById(city.getCountry().getCountryId())
            );
        }
        return ResponseEntity.ok(cityService.saveCity(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Integer id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok("City deleted successfully");
    }
}