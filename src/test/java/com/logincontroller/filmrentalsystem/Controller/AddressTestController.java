package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.AddressController;
import com.logincontroller.filmrentalsystem.dto.AddressDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.City;
import com.logincontroller.filmrentalsystem.service.AddressService;
import com.logincontroller.filmrentalsystem.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class AddressTestController {

    @Mock
    AddressService addressService;

    @Mock
    CityService cityService;

    AddressController addressController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        addressController = new AddressController(addressService, cityService);
    }

    @Test
    public void createAddress_withCityId_resolvesCityAndSaves() {
        Short cityId = 5;

        City city = new City();
        city.setCityId(cityId);

        Address input = new Address();
        input.setAddressId((short) 1);
        input.setAddress("Main St");
        input.setDistrict("District");
        input.setPhone("999999");
        input.setLocation("Loc");
        input.setCity(city);

        City resolvedCity = new City();
        resolvedCity.setCityId(cityId);

        AddressDTO expectedDto = new AddressDTO();
        expectedDto.setAddressId((short) 1);
        expectedDto.setAddress("Main St");

        when(cityService.getEntityById(cityId)).thenReturn(resolvedCity);
        when(addressService.saveAddress(input)).thenReturn(expectedDto);

        ResponseEntity<AddressDTO> response = addressController.createAddress(input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expectedDto, response.getBody());
        verify(cityService).getEntityById(cityId);
        verify(addressService).saveAddress(input);
    }

    @Test
    public void getAddressById_delegatesToService() {
        Short id = 1;
        AddressDTO expected = new AddressDTO();
        expected.setAddressId(id);

        when(addressService.getAddressById(id)).thenReturn(expected);

        ResponseEntity<AddressDTO> response = addressController.getAddressById(id);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(addressService).getAddressById(id);
    }
}

