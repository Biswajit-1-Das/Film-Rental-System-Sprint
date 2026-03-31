package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.CityDTO;
import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.model.Country;
import com.logincontroller.filmrentalsystem.repository.CityRepository;
import com.logincontroller.filmrentalsystem.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class CityTestService {

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    CityService cityService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCityByCityName_mapsToDto() {
        String cityName = "Kolkata";
        Short cityId = 10;

        Country country = new Country();
        country.setCountryId((short) 1);
        country.setCountry("India");

        City entity = new City();
        entity.setCityId(cityId);
        entity.setCity(cityName);
        entity.setCountry(country);
        entity.setLastUpdate(LocalDateTime.now());

        when(cityRepository.findByCity(cityName)).thenReturn(Optional.of(entity));

        CityDTO dto = cityService.getCityByCityName(cityName);

        assertEquals(cityId, dto.getCityId());
        assertEquals(cityName, dto.getCity());
        assertEquals((short) 1, dto.getCountryId());
        assertEquals("India", dto.getCountryName());
        verify(cityRepository).findByCity(cityName);
    }

    @Test
    public void saveCity_savesAndReturnsDto() {
        City input = new City();
        input.setCityId((short) 2);
        input.setCity("Mumbai");
        input.setLastUpdate(LocalDateTime.now());

        when(cityRepository.save(input)).thenReturn(input);

        CityDTO dto = cityService.saveCity(input);

        assertEquals("Mumbai", dto.getCity());
        verify(cityRepository).save(input);
    }

    @Test
    public void deleteCity_deletesById() {
        Short id = 2;
        doNothing().when(cityRepository).deleteById(id);

        cityService.deleteCity(id);

        verify(cityRepository).deleteById(id);
    }
}

