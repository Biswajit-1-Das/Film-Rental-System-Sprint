package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.CityDTO;
import com.logincontroller.filmrentalsystem.exception.ResourceNotFoundException;
import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    private CityDTO toDTO(City city) {
        CityDTO dto = new CityDTO();
        dto.setCityId(city.getCityId());
        dto.setCity(city.getCity());
        dto.setLastUpdate(city.getLastUpdate());
        if (city.getCountry() != null) {
            dto.setCountryId(city.getCountry().getCountryId());
            dto.setCountryName(city.getCountry().getCountry());
        }
        return dto;
    }

    public City getEntityById(Short id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City not found with id: " + id));
    }

    public List<CityDTO> getAllCities() {
        List<City> cities = cityRepository.findAll();
        List<CityDTO> result = new ArrayList<>();

        for (City city : cities) {
            result.add(toDTO(city));
        }

        return result;
    }

    public CityDTO getCityById(Short id) {
        City city = getEntityById(id);
        return toDTO(city);
    }

    public List<CityDTO> getCitiesByCountry(Short countryId) {
        List<City> cities = cityRepository.findByCountryCountryId(countryId);
        List<CityDTO> result = new ArrayList<>();

        for (City city : cities) {
            result.add(toDTO(city));
        }

        return result;
    }

    public CityDTO getCityByCityName(String cityName) {
        City city = cityRepository.findByCity(cityName)
                .orElseThrow(() -> new ResourceNotFoundException("City not found: " + cityName));
        return toDTO(city);
    }

    public List<CityDTO> getCitiesByCountryName(String countryName) {
        List<City> cities = cityRepository.findByCountry_CountryIgnoreCase(countryName);
        List<CityDTO> result = new ArrayList<>();

        for (City city : cities) {
            result.add(toDTO(city));
        }

        return result;
    }

    public CityDTO saveCity(City city) {
        return toDTO(cityRepository.save(city));
    }

    public void deleteCity(Short id) {
        cityRepository.deleteById(id);
    }
}
