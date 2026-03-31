package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.StaffController;
import com.logincontroller.filmrentalsystem.dto.StaffResponseDTO;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class StaffTestController {

    @Mock
    StaffService staffService;

    StaffController staffController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        staffController = new StaffController(staffService);
    }

    @Test
    public void createStaff_delegatesToService() {
        Staff input = new Staff();
        input.setStaffId((byte) 1);

        StaffResponseDTO expected = new StaffResponseDTO();
        expected.setStaffId((byte) 1);

        when(staffService.createStaff(input)).thenReturn(expected);

        ResponseEntity<StaffResponseDTO> response = staffController.createStaff(input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(staffService).createStaff(input);
    }

    @Test
    public void deactivateStaff_returnsOkMessage() {
        Byte id = 1;
        doNothing().when(staffService).deactivateStaff(id);

        ResponseEntity<String> response = staffController.deactivateStaff(id);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Staff deactivated successfully", response.getBody());
        verify(staffService).deactivateStaff(id);
    }
}

