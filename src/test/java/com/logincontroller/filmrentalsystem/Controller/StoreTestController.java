package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.StoreController;
import com.logincontroller.filmrentalsystem.dto.StoreResponseDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.service.AddressService;
import com.logincontroller.filmrentalsystem.service.StaffService;
import com.logincontroller.filmrentalsystem.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class StoreTestController {

    @Mock
    StoreService storeService;

    @Mock
    AddressService addressService;

    @Mock
    StaffService staffService;

    StoreController storeController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        storeController = new StoreController(storeService, addressService, staffService);
    }

    @Test
    public void createStore_resolvesAddressAndManagerAndSaves() {
        Byte storeId = 1;
        Short addressId = 2;
        Byte staffId = 3;

        Store input = new Store();
        input.setStoreId(storeId);

        Address stubAddress = new Address();
        stubAddress.setAddressId(addressId);
        input.setAddress(stubAddress);

        Staff stubManager = new Staff();
        stubManager.setStaffId(staffId);
        input.setManagerStaff(stubManager);

        Address resolvedAddress = new Address();
        resolvedAddress.setAddressId(addressId);
        resolvedAddress.setAddress("Main St");

        Staff resolvedManager = new Staff();
        resolvedManager.setStaffId(staffId);
        resolvedManager.setFirstName("A");
        resolvedManager.setLastName("B");

        StoreResponseDTO expectedDto = new StoreResponseDTO();
        expectedDto.setStoreId(storeId);
        expectedDto.setAddressId(addressId);

        when(addressService.getEntityById(addressId)).thenReturn(resolvedAddress);
        when(staffService.getEntityById(staffId)).thenReturn(resolvedManager);
        when(storeService.saveStore(any(Store.class))).thenReturn(expectedDto);

        ResponseEntity<StoreResponseDTO> response = storeController.createStore(input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expectedDto, response.getBody());

        ArgumentCaptor<Store> captor = ArgumentCaptor.forClass(Store.class);
        verify(storeService).saveStore(captor.capture());
        assertSame(resolvedAddress, captor.getValue().getAddress());
        assertSame(resolvedManager, captor.getValue().getManagerStaff());
    }
}

