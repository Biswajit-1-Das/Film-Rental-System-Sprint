package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.CountryController;
import com.logincontroller.filmrentalsystem.dto.CountryDTO;
import com.logincontroller.filmrentalsystem.model.Country;
import com.logincontroller.filmrentalsystem.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class CountryTestController {

    @Mock
    CountryService countryService;

    CountryController countryController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        countryController = new CountryController(countryService);
    }

    @Test
    public void updateCountry_delegatesAndReturnsSavedDto() {
        Short id = 1;
        Country input = new Country();
        input.setCountry("India");

        Country existing = new Country();
        existing.setCountry("Old");

        CountryDTO savedDto = new CountryDTO();
        savedDto.setCountryId(id);
        savedDto.setCountry("India");

        when(countryService.getEntityById(id)).thenReturn(existing);
        when(countryService.saveCountry(existing)).thenReturn(savedDto);

        ResponseEntity<CountryDTO> response = countryController.updateCountry(id, input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(savedDto, response.getBody());
        verify(countryService).getEntityById(id);
        verify(countryService).saveCountry(existing);
    }

    @Test
    public void countryExists_delegatesToService() {
        when(countryService.countryExistsIgnoreCase("India")).thenReturn(true);

        ResponseEntity<Boolean> response = countryController.countryExists("India");

        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody());
        verify(countryService).countryExistsIgnoreCase("India");
    }
}

