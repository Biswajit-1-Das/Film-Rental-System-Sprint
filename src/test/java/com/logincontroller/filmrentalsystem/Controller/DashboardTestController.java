package com.logincontroller.filmrentalsystem.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.model.Staff;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DashboardTestController {

    @Mock
    HttpSession session;

    DashboardController dashboardController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        dashboardController = new DashboardController();
    }

    @Test
    public void dashboard_notLoggedIn_redirectsToLogin() {
        when(session.getAttribute("user")).thenReturn(null);

        String view = dashboardController.dashboard(session);

        assertEquals("redirect:/login", view);
    }

    @Test
    public void dashboard_nonAdmin_redirectsHome() {
        Staff staff = new Staff();
        staff.setUsername("staff");

        when(session.getAttribute("user")).thenReturn(staff);

        String view = dashboardController.dashboard(session);

        assertEquals("redirect:/home", view);
    }
}

