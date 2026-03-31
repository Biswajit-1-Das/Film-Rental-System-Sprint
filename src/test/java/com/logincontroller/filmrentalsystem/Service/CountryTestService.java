package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.CountryDTO;
import com.logincontroller.filmrentalsystem.model.Country;
import com.logincontroller.filmrentalsystem.repository.CountryRepository;
import com.logincontroller.filmrentalsystem.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class CountryTestService {

    @Mock
    CountryRepository countryRepository;

    @InjectMocks
    CountryService countryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void countryExistsIgnoreCase_delegatesToRepository() {
        when(countryRepository.existsByCountryIgnoreCase("India")).thenReturn(true);

        boolean exists = countryService.countryExistsIgnoreCase("India");

        assertTrue(exists);
        verify(countryRepository).existsByCountryIgnoreCase("India");
    }

    @Test
    public void getCountryByName_mapsToDto() {
        String name = "India";
        Short id = 1;

        Country entity = new Country();
        entity.setCountryId(id);
        entity.setCountry(name);
        entity.setLastUpdate(LocalDateTime.now());

        when(countryRepository.findByCountryIgnoreCase(name)).thenReturn(Optional.of(entity));

        CountryDTO dto = countryService.getCountryByName(name);

        assertEquals(id, dto.getCountryId());
        assertEquals(name, dto.getCountry());
        verify(countryRepository).findByCountryIgnoreCase(name);
    }
}

