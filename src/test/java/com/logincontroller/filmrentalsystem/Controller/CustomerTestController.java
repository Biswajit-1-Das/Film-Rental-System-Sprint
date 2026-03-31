package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.CustomerController;
import com.logincontroller.filmrentalsystem.dto.CustomerResponseDTO;
import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class CustomerTestController {

    @Mock
    CustomerService customerService;

    CustomerController customerController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
    public void createCustomer_delegatesToService() {
        Customer input = new Customer();
        input.setCustomerId((short) 1);

        CustomerResponseDTO expected = new CustomerResponseDTO();
        expected.setCustomerId((short) 1);

        when(customerService.createCustomer(input)).thenReturn(expected);

        ResponseEntity<CustomerResponseDTO> response = customerController.createCustomer(input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(customerService).createCustomer(input);
    }

    @Test
    public void activateCustomer_returnsMessage() {
        Short id = 1;
        doNothing().when(customerService).activateCustomer(id);

        ResponseEntity<String> response = customerController.activateCustomer(id);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Customer " + id + " activated", response.getBody());
        verify(customerService).activateCustomer(id);
    }
}

