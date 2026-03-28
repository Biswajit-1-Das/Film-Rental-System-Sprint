package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.StaffDTO;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    private StaffDTO toDTO(Staff staff) {
        StaffDTO dto = new StaffDTO();
        dto.setStaffId(staff.getStaffId());
        dto.setFirstName(staff.getFirstName());
        dto.setLastName(staff.getLastName());
        dto.setEmail(staff.getEmail());
        dto.setActive(staff.getActive());
        dto.setUsername(staff.getUsername());
        dto.setLastUpdate(staff.getLastUpdate());
        if (staff.getAddress() != null) {
            dto.setAddressId(staff.getAddress().getAddressId());
            dto.setAddressLine(staff.getAddress().getAddress());
            if (staff.getAddress().getCity() != null) {
                dto.setCity(staff.getAddress().getCity().getCity());
                if (staff.getAddress().getCity().getCountry() != null) {
                    dto.setCountry(
                            staff.getAddress().getCity().getCountry().getCountry()
                    );
                }
            }
        }
        return dto;
    }

    Staff getEntityById(Integer id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
    }

    public List<StaffDTO> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public List<StaffDTO> getStaffByStore(Integer storeId) {
        return staffRepository.findByStoreStoreId(storeId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Staff> getStaffByAddress(Integer addressId){
        return staffRepository.findByAddressAddressId(addressId);

    }


    public StaffDTO getStaffById(Integer id) {
        return toDTO(getEntityById(id));
    }

    @Transactional
    public StaffDTO createStaff(Staff staff) {
        staff.setActive(true);
        return toDTO(staffRepository.save(staff));
    }

    @Transactional
    public StaffDTO updateStaff(Integer id, Staff updatedData) {
        Staff existing = getEntityById(id);
        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setEmail(updatedData.getEmail());
        existing.setAddress(updatedData.getAddress());
        return toDTO(staffRepository.save(existing));
    }

    @Transactional
    public void uploadPicture(Integer id, MultipartFile file) throws IOException {
        Staff staff = getEntityById(id);
        staff.setPicture(file.getBytes());
        staffRepository.save(staff);
    }

    public byte[] getPicture(Integer id) {
        Staff staff = getEntityById(id);
        if (staff.getPicture() == null) {
            throw new RuntimeException("No picture found for staff id: " + id);
        }
        return staff.getPicture();
    }

    @Transactional
    public void deactivateStaff(Integer id) {
        Staff staff = getEntityById(id);
        staff.setActive(false);
        staffRepository.save(staff);
    }

    @Transactional
    public void activateStaff(Integer id) {
        Staff staff = getEntityById(id);
        staff.setActive(true);
        staffRepository.save(staff);
    }

    public Staff login(String username, String password) {

        Staff staff = staffRepository.findByUsername(username);

        if (staff == null)
        {
            throw new RuntimeException("User not found ");
        }

        if (!staff.getPassword().equals(password))
        {
            throw new RuntimeException("Invalid password");
        }

        return staff;
    }
}