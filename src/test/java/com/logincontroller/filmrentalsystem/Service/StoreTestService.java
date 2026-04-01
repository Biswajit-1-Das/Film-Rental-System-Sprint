package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.StoreResponseDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import com.logincontroller.filmrentalsystem.repository.CustomerRepository;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import com.logincontroller.filmrentalsystem.repository.StoreRepository;
import com.logincontroller.filmrentalsystem.service.StaffService;
import com.logincontroller.filmrentalsystem.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class StoreTestService {

    @Mock
    StoreRepository storeRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    StaffRepository staffRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    StaffService staffService;

    @InjectMocks
    StoreService storeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void linkStoreAddress_linksAndReturnsDto() {
        Byte storeId = 1;
        Short addressId = 2;

        Store store = new Store();
        store.setStoreId(storeId);

        Address address = new Address();
        address.setAddressId(addressId);
        address.setAddress("Main St");

        Store savedStore = new Store();
        savedStore.setStoreId(storeId);
        savedStore.setAddress(address);

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(address));
        when(storeRepository.save(any(Store.class))).thenReturn(savedStore);

        StoreResponseDTO dto = storeService.linkStoreAddress(storeId, addressId);

        assertEquals(storeId, dto.getStoreId());
        assertEquals(addressId, dto.getAddressId());
        verify(storeRepository).save(any(Store.class));
    }

    @Test
    public void updateStorePhone_updatesAddressAndReturnsDto() {
        Byte storeId = 1;
        Short addressId = 2;
        String phone = "999";

        Address address = new Address();
        address.setAddressId(addressId);
        address.setAddress("Main St");
        address.setPhone("old");
        address.setDistrict("D");
        address.setLocation("L");
        address.setLastUpdate(LocalDateTime.now());

        Store store = new Store();
        store.setStoreId(storeId);
        store.setAddress(address);

        when(storeRepository.findById(storeId)).thenReturn(Optional.of(store));
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        StoreResponseDTO dto = storeService.updateStorePhone(storeId, phone);

        assertEquals(addressId, dto.getAddressId());
        verify(addressRepository).save(any(Address.class));
    }
}

