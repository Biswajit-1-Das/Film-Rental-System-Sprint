package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.CityDTO;
import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
    }

    public List<CityDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CityDTO getCityById(Short id) {
        return toDTO(getEntityById(id));
    }

    public List<CityDTO> getCitiesByCountry(Short countryId) {
        return cityRepository.findByCountryCountryId(countryId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CityDTO getCityByCityName(String cityName) {
        City city = cityRepository.findByCity(cityName)
                .orElseThrow(() -> new RuntimeException("City not found: " + cityName));
        return toDTO(city);
    }

    public List<CityDTO> getCitiesByCountryName(String countryName) {
        return cityRepository.findByCountry_CountryIgnoreCase(countryName)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CityDTO saveCity(City city) {
        return toDTO(cityRepository.save(city));
    }

    public void deleteCity(Short id) {
        cityRepository.deleteById(id);
    }
}
