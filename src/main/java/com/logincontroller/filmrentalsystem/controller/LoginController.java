package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@CrossOrigin(origins="http://10.191.27.14:9090")
public class LoginController {

    @Autowired
    private StaffService staffService;   // ✅ inject here

    @PostMapping("/api/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        try {
            Staff staff = staffService.login(username, password); // ✅ no null

            session.setAttribute("user", staff);

            if (staff.getUsername().equals("admin")) {
                return "redirect:/dashboard";
            }

            return "redirect:/home";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}