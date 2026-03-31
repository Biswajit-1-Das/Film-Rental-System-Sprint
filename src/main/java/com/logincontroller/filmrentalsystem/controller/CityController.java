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
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/search/name")
    public ResponseEntity<CityDTO> getCityByCityName(@RequestParam String city) {
        return ResponseEntity.ok(cityService.getCityByCityName(city));
    }

    @GetMapping("/search/country")
    public ResponseEntity<List<CityDTO>> getCitiesByCountryName(@RequestParam String country) {
        return ResponseEntity.ok(cityService.getCitiesByCountryName(country));
    }

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<CityDTO>> getCitiesByCountry(@PathVariable Short countryId) {
        return ResponseEntity.ok(cityService.getCitiesByCountry(countryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable Short id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @PostMapping("/country/{countryId}")
    public ResponseEntity<CityDTO> createCity(@PathVariable Short countryId,
                                              @RequestBody City city) {
        city.setCountry(countryService.getEntityById(countryId));
        return ResponseEntity.ok(cityService.saveCity(city));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityDTO> updateCity(@PathVariable Short id,
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
    public ResponseEntity<String> deleteCity(@PathVariable Short id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok("City deleted successfully");
    }
}
