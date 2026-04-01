package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.CustomerResponseDTO;
import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import com.logincontroller.filmrentalsystem.repository.CustomerRepository;
import com.logincontroller.filmrentalsystem.repository.StoreRepository;
import com.logincontroller.filmrentalsystem.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class CustomerTestService {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    CustomerService customerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateEmail_updatesEntityAndReturnsDto() {
        Short id = 1;
        String email = "new@mail.com";

        Customer existing = new Customer();
        existing.setCustomerId(id);
        existing.setEmail("old@mail.com");
        existing.setLastUpdate(LocalDateTime.now());

        when(customerRepository.findById(id)).thenReturn(Optional.of(existing));
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CustomerResponseDTO dto = customerService.updateEmail(id, email);

        assertEquals(id, dto.getCustomerId());
        assertEquals(email, dto.getEmail());
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    public void activateCustomer_setsActiveToOneAndSaves() {
        Short id = 1;

        Customer existing = new Customer();
        existing.setCustomerId(id);
        existing.setActive((byte) 0);

        when(customerRepository.findById(id)).thenReturn(Optional.of(existing));
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        customerService.activateCustomer(id);

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());
        assertEquals((byte) 1, captor.getValue().getActive());
    }
}

