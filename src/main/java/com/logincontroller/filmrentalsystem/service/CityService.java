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
        // flatten country into just id and name
        if (city.getCountry() != null) {
            dto.setCountryId(city.getCountry().getCountryId());
            dto.setCountryName(city.getCountry().getCountry());
        }
        return dto;
    }

    public City getEntityById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found with id: " + id));
    }

    public List<CityDTO> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CityDTO getCityById(Integer id) {
        return toDTO(getEntityById(id));
    }

    public List<CityDTO> getCitiesByCountry(Integer countryId) {
        return cityRepository.findByCountryCountryId(countryId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public CityDTO saveCity(City city) {
        return toDTO(cityRepository.save(city));
    }

    public void deleteCity(Integer id) {
        cityRepository.deleteById(id);
    }
}