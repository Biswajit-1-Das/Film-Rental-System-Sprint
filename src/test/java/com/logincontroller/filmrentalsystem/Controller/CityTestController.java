package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.CityController;
import com.logincontroller.filmrentalsystem.dto.CityDTO;
import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.model.Country;
import com.logincontroller.filmrentalsystem.service.CityService;
import com.logincontroller.filmrentalsystem.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class CityTestController {

    @Mock
    CityService cityService;

    @Mock
    CountryService countryService;

    CityController cityController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        cityController = new CityController(cityService, countryService);
    }

    @Test
    public void createCity_setsCountryAndSaves() {
        Short countryId = 1;
        City input = new City();
        input.setCityId((short) 10);
        input.setCity("Kolkata");

        Country country = new Country();
        country.setCountryId(countryId);
        country.setCountry("India");

        CityDTO expected = new CityDTO();
        expected.setCityId(input.getCityId());
        expected.setCity("Kolkata");

        when(countryService.getEntityById(countryId)).thenReturn(country);
        when(cityService.saveCity(any(City.class))).thenReturn(expected);

        ResponseEntity<CityDTO> response = cityController.createCity(countryId, input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());

        ArgumentCaptor<City> captor = ArgumentCaptor.forClass(City.class);
        verify(cityService).saveCity(captor.capture());
        assertSame(country, captor.getValue().getCountry());
    }

    @Test
    public void getCityById_delegatesToService() {
        Short id = 2;
        CityDTO expected = new CityDTO();
        expected.setCityId(id);

        when(cityService.getCityById(id)).thenReturn(expected);

        ResponseEntity<CityDTO> response = cityController.getCityById(id);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(cityService).getCityById(id);
    }
}

