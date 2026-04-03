package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class LoginTestController {

    @Mock
    StaffService staffService;

    @InjectMocks
    LoginController loginController;

    @Mock
    HttpSession session;

    @Mock
    Model model;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void login_adminRedirectsToDashboardAndStoresUser() {
        String username = "admin";
        String password = "pass";

        Staff staff = new Staff();
        staff.setUsername("admin");

        when(staffService.login(username, password)).thenReturn(staff);

        String view = loginController.login(username, password, session, model);

        assertEquals("redirect:/dashboard", view);
        verify(session).setAttribute("user", staff);
    }

    @Test
    public void login_serviceThrowsRendersLoginWithError() {
        String username = "user";
        String password = "bad";

        when(staffService.login(username, password)).thenThrow(new RuntimeException("Invalid password"));

        String view = loginController.login(username, password, session, model);

        assertEquals("login", view);
        verify(model).addAttribute("error", "Invalid password");
        verify(session, never()).setAttribute(eq("user"), any());
    }
}

