package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Staff;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins="http://10.191.27.14:9090")
public class DashboardController {

    @GetMapping("/api/dashboard")
    public String dashboard(HttpSession session) {

        Staff staff = (Staff) session.getAttribute("user");

        // ✅ Not logged in
        if (staff == null) {
            return "redirect:/login";
        }

        // ✅ Not admin → block
        if (!staff.getUsername().equals("admin")) {
            return "redirect:/home";
        }

        return "dashboard";
    }
}