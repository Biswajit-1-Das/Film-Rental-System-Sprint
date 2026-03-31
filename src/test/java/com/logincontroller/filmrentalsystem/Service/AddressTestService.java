package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.AddressDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import com.logincontroller.filmrentalsystem.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AddressTestService {

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressService addressService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAddressById_mapsToDto() {
        Short id = 1;
        Address address = new Address();
        address.setAddressId(id);
        address.setAddress("Main St");
        address.setAddress2("Apt 1");
        address.setDistrict("District");
        address.setPostalCode("12345");
        address.setPhone("999999");
        address.setLocation("Loc");
        address.setLastUpdate(LocalDateTime.now());

        when(addressRepository.findById(id)).thenReturn(Optional.of(address));

        AddressDTO dto = addressService.getAddressById(id);

        assertEquals(id, dto.getAddressId());
        assertEquals("Main St", dto.getAddress());
        assertEquals("District", dto.getDistrict());
        verify(addressRepository).findById(id);
    }

    @Test
    public void saveAddress_savesAndReturnsDto() {
        Address input = new Address();
        input.setAddressId((short) 1);
        input.setAddress("Main St");
        input.setDistrict("District");
        input.setPhone("999999");
        input.setLocation("Loc");
        input.setLastUpdate(LocalDateTime.now());

        when(addressRepository.save(input)).thenReturn(input);

        AddressDTO dto = addressService.saveAddress(input);

        assertEquals("Main St", dto.getAddress());
        verify(addressRepository).save(input);
    }

    @Test
    public void deleteAddress_deletesById() {
        Short id = 1;
        doNothing().when(addressRepository).deleteById(id);

        addressService.deleteAddress(id);

        verify(addressRepository).deleteById(id);
    }
}

