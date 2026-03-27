package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // ✅ CREATE
    @PostMapping
    public Staff createStaff(@RequestParam Integer addressId,
                             @RequestParam Integer storeId,
                             @RequestBody Staff staff) {

        return staffService.createStaff(addressId, storeId, staff);
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Integer id) {
        return staffService.getStaffById(id);
    }

    // ✅ GET ALL
    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    // ✅ GET BY ADDRESS
    @GetMapping("/address/{addressId}")
    public List<Staff> getByAddress(@PathVariable Integer addressId) {
        return staffService.getStaffByAddress(addressId);
    }

    // ✅ GET BY STORE
    @GetMapping("/store/{storeId}")
    public List<Staff> getByStore(@PathVariable Integer storeId) {
        return staffService.getStaffByStore(storeId);
    }

    // ✅ GET BY USERNAME
    @GetMapping("/username/{username}")
    public Staff getByUsername(@PathVariable String username) {
        return staffService.getByUsername(username);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Integer id,
                             @RequestParam Integer addressId,
                             @RequestParam Integer storeId,
                             @RequestBody Staff staff) {

        return staffService.updateStaff(id, staff, addressId, storeId);
    }
}