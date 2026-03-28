package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.*;
import com.logincontroller.filmrentalsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService{

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private StoresRepository storeRepository;

    // ✅ CREATE
    public Staff createStaff(Integer addressId, Integer storeId, Staff staff) {

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        staff.setAddress(address);
        staff.setStore(store);

        return staffRepository.save(staff);
    }

    // ✅ GET BY ID
    public Staff getStaffById(Integer id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
    }

    // ✅ GET ALL
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // ✅ GET BY ADDRESS
    public List<Staff> getStaffByAddress(Integer addressId) {
        return staffRepository.findByAddressAddressId(addressId);
    }

    // ✅ GET BY STORE
    public List<Staff> getStaffByStore(Integer storeId) {
        return staffRepository.findByStoreStoreId(storeId);
    }

    // ✅ GET BY USERNAME
    public Staff getByUsername(String username) {
        return staffRepository.findByUsername(username);
    }

    // ✅ UPDATE
    public Staff updateStaff(Integer id, Staff updatedStaff,
                             Integer addressId, Integer storeId) {

        Staff existing = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        existing.setFirstName(updatedStaff.getFirstName());
        existing.setLastName(updatedStaff.getLastName());
        existing.setEmail(updatedStaff.getEmail());
        existing.setActive(updatedStaff.getActive());
        existing.setUsername(updatedStaff.getUsername());
        existing.setPassword(updatedStaff.getPassword());
        existing.setPicture(updatedStaff.getPicture());

        existing.setAddress(address);
        existing.setStore(store);

        return staffRepository.save(existing);
    }

    public Staff login(String username, String password) {
        Staff staff = staffRepository.findByUsername(username);

        if (staff == null) {
            throw new RuntimeException("User not found");
        }

        if (!staff.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return staff;
    }
}