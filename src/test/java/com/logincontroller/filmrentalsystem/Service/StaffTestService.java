package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.StaffResponseDTO;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import com.logincontroller.filmrentalsystem.repository.StoreRepository;
import com.logincontroller.filmrentalsystem.service.StaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class StaffTestService {

    @Mock
    StaffRepository staffRepository;

    @Mock
    AddressRepository addressRepository;

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StaffService staffService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createStaff_setsActiveAndReturnsDto() {
        Staff input = new Staff();
        input.setStaffId((byte) 1);
        input.setFirstName("A");
        input.setLastName("B");
        input.setEmail("a@b.com");
        input.setUsername("user");
        input.setPassword("pass");
        input.setLastUpdate(LocalDateTime.now());

        when(staffRepository.save(input)).thenReturn(input);

        StaffResponseDTO dto = staffService.createStaff(input);

        assertNotNull(dto.getActive());
        assertTrue(dto.getActive());
        assertEquals("user", dto.getUsername());
        verify(staffRepository).save(input);
    }

    @Test
    public void deactivateStaff_setsActiveToZeroAndSaves() {
        Byte id = 1;

        Staff existing = new Staff();
        existing.setStaffId(id);
        existing.setActive((byte) 1);

        when(staffRepository.findById(id)).thenReturn(Optional.of(existing));
        when(staffRepository.save(any(Staff.class))).thenAnswer(invocation -> invocation.getArgument(0));

        staffService.deactivateStaff(id);

        ArgumentCaptor<Staff> captor = ArgumentCaptor.forClass(Staff.class);
        verify(staffRepository).save(captor.capture());
        assertEquals((byte) 0, captor.getValue().getActive());
    }

    @Test
    public void login_successfulCredentialsReturnStaff() {
        String username = "user";
        String password = "pass";

        Staff staff = new Staff();
        staff.setUsername(username);
        staff.setPassword(password);

        when(staffRepository.findByUsername(username)).thenReturn(staff);

        Staff out = staffService.login(username, password);

        assertSame(staff, out);
    }
}

