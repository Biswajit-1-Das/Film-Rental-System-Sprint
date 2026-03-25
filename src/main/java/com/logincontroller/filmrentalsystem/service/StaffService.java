package com.logincontroller.filmrentalsystem.service;


import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService implements UserDetailsService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Staff staff = staffRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(
                        "No staff account found for username: " + username));

        String role = isManager(staff) ? "ROLE_MANAGER" : "ROLE_STAFF";

        return new org.springframework.security.core.userdetails.User(
                staff.getUsername(),
                staff.getPassword(),
                List.of(new SimpleGrantedAuthority(role))
        );
    }

    private boolean isManager(Staff staff) {
        return staff.getStore() != null
                && staff.getStore().getManagerStaff() != null
                && staff.getStore().getManagerStaff().getStaffId()
                .equals(staff.getStaffId());
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public List<Staff> getActiveStaff() {
        return staffRepository.findByActiveTrue();
    }

    public List<Staff> getStaffByStore(Integer storeId) {
        return staffRepository.findByStoreStoreId(storeId);
    }

    public Staff getStaffById(Integer id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Staff not found with id: " + id));
    }

    @Transactional
    public Staff createStaff(Staff staff) {
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staff.setActive(true);
        return staffRepository.save(staff);
    }

    @Transactional
    public Staff updateStaff(Integer id, Staff updatedData) {
        Staff existing = getStaffById(id);
        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setEmail(updatedData.getEmail());
        existing.setAddress(updatedData.getAddress());
        existing.setStore(updatedData.getStore());
        return staffRepository.save(existing);
    }

    @Transactional
    public void changePassword(Integer id, String rawNewPassword) {
        Staff staff = getStaffById(id);
        staff.setPassword(passwordEncoder.encode(rawNewPassword));
        staffRepository.save(staff);
    }

    @Transactional
    public void uploadPicture(Integer id, MultipartFile file) throws IOException {
        Staff staff = getStaffById(id);
        staff.setPicture(file.getBytes());
        staffRepository.save(staff);
    }

    public byte[] getPicture(Integer id) {
        Staff staff = getStaffById(id);
        if (staff.getPicture() == null) {
            throw new RuntimeException(
                    "No picture on file for staff id: " + id);
        }
        return staff.getPicture();
    }

    @Transactional
    public void deactivateStaff(Integer id) {
        Staff staff = getStaffById(id);
        staff.setActive(false);
        staffRepository.save(staff);
    }

    @Transactional
    public void activateStaff(Integer id) {
        Staff staff = getStaffById(id);
        staff.setActive(true);
        staffRepository.save(staff);
    }

    public List<Object[]> getStaffListView() {
        return staffRepository.getStaffListView();
    }
}