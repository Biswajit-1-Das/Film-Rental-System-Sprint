package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    // GET /cities
    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    // GET /cities/{id}
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable int id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    // GET /cities/search?name=London
    @GetMapping("/search")
    public ResponseEntity<City> getCityByName(@RequestParam String name) {
        return ResponseEntity.ok(cityService.getCityByName(name));
    }

    // GET /cities/country/{countryId}
    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<City>> getCitiesByCountryId(
            @PathVariable int countryId) {
        return ResponseEntity.ok(cityService.getCitiesByCountryId(countryId));
    }

    // GET /cities/country/name?name=India
    @GetMapping("/country/name")
    public ResponseEntity<List<City>> getCitiesByCountryName(
            @RequestParam String name) {
        return ResponseEntity.ok(cityService.getCitiesByCountryName(name));
    }

    // POST /cities
    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        return ResponseEntity.ok(cityService.createCity(city));
    }

    // PUT /cities/{id}
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(
            @PathVariable int id,
            @RequestBody City updatedData) {
        return ResponseEntity.ok(cityService.updateCity(id, updatedData));
    }

    // DELETE /cities/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable int id) {
        cityService.deleteCity(id);
        return ResponseEntity.ok("City deleted successfully");
    }
}