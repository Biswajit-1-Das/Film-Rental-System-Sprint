package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.AddressResponseDTO;
import com.logincontroller.filmrentalsystem.dto.StaffResponseDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import com.logincontroller.filmrentalsystem.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final AddressRepository addressRepository;
    private final StoreRepository storeRepository;

    public StaffResponseDTO mapStaff(Staff staff) {
        return toResponseDTO(staff);
    }

    private StaffResponseDTO toResponseDTO(Staff staff) {
        StaffResponseDTO dto = new StaffResponseDTO();
        dto.setStaffId(staff.getStaffId());
        dto.setFirstName(staff.getFirstName());
        dto.setLastName(staff.getLastName());
        dto.setPicture(staff.getPicture());
        dto.setEmail(staff.getEmail());
        dto.setActive(staff.getActive() != 0 && staff.getActive() != 0);
        dto.setUsername(staff.getUsername());
        dto.setLastUpdate(staff.getLastUpdate());

        if (staff.getStore() != null) {
            dto.setStoreId(staff.getStore().getStoreId());
        }

        if (staff.getAddress() != null) {
            dto.setAddressId(staff.getAddress().getAddressId());
            dto.setAddressLine(staff.getAddress().getAddress());
            dto.setPhone(staff.getAddress().getPhone());

            if (staff.getAddress().getCity() != null) {
                dto.setCity(staff.getAddress().getCity().getCity());

                if (staff.getAddress().getCity().getCountry() != null) {
                    dto.setCountry(staff.getAddress().getCity().getCountry().getCountry());
                }
            }
        }

        return dto;
    }

    private AddressResponseDTO toAddressResponseDTO(Address address) {
        AddressResponseDTO dto = new AddressResponseDTO();
        dto.setAddressId(address.getAddressId());
        dto.setAddress(address.getAddress());
        dto.setAddress2(address.getAddress2());
        dto.setDistrict(address.getDistrict());
        dto.setPostalCode(address.getPostalCode());
        dto.setPhone(address.getPhone());
        dto.setLocation(address.getLocation());
        dto.setLastUpdate(address.getLastUpdate());

        if (address.getCity() != null) {
            dto.setCityId(address.getCity().getCityId());
            dto.setCityName(address.getCity().getCity());

            if (address.getCity().getCountry() != null) {
                dto.setCountryId(address.getCity().getCountry().getCountryId());
                dto.setCountryName(address.getCity().getCountry().getCountry());
            }
        }

        return dto;
    }

    public Staff getEntityById(Byte id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> getAllStaff() {
        List<Staff> list = staffRepository.findAll();
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : list) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> getActiveStaff() {
        List<Staff> list = staffRepository.findByActive((byte) 1);
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : list) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> getStaffByStore(Byte storeId) {
        List<Staff> list = staffRepository.findByStoreStoreId(storeId);
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : list) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    public List<Staff> getStaffByAddress(Short addressId) {
        return staffRepository.findByAddressAddressId(addressId);
    }

    @Transactional(readOnly = true)
    public StaffResponseDTO getStaffById(Byte id) {
        return toResponseDTO(getEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> searchByLastName(String ln) {
        List<Staff> list = staffRepository.findByLastNameContainingIgnoreCase(ln);
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : list) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> searchByFirstName(String fn) {
        List<Staff> list = staffRepository.findByFirstNameContainingIgnoreCase(fn);
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : list) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public StaffResponseDTO getStaffByEmail(String email) {
        Staff staff = staffRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Staff not found with email: " + email));

        return toResponseDTO(staff);
    }

    @Transactional(readOnly = true)
    public AddressResponseDTO getAddressForStaff(Byte id) {
        Staff staff = getEntityById(id);
        return toAddressResponseDTO(staff.getAddress());
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> getStaffByCity(String city) {
        List<Staff> list = staffRepository.findByAddress_City_City(city);
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : list) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> getStaffByCountry(String country) {
        List<Staff> list = staffRepository.findByAddress_City_Country_Country(country);
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : list) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> getStaffByPhone(String phone) {
        List<Staff> list = staffRepository.findByAddress_Phone(phone);
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : list) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional
    public StaffResponseDTO createStaff(Staff staff) {
        staff.setActive((byte) 1);
        return toResponseDTO(staffRepository.save(staff));
    }

    @Transactional
    public StaffResponseDTO updateStaff(Byte id, Staff updatedData) {
        Staff existing = getEntityById(id);
        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setEmail(updatedData.getEmail());
        existing.setAddress(updatedData.getAddress());
        return toResponseDTO(staffRepository.save(existing));
    }

    @Transactional
    public StaffResponseDTO updateFirstName(Byte id, String firstName) {
        Staff s = getEntityById(id);
        s.setFirstName(firstName);
        return toResponseDTO(staffRepository.save(s));
    }

    @Transactional
    public StaffResponseDTO updateLastName(Byte id, String lastName) {
        Staff s = getEntityById(id);
        s.setLastName(lastName);
        return toResponseDTO(staffRepository.save(s));
    }

    @Transactional
    public StaffResponseDTO updateEmail(Byte id, String email) {
        Staff s = getEntityById(id);
        s.setEmail(email);
        return toResponseDTO(staffRepository.save(s));
    }

    @Transactional
    public StaffResponseDTO updateStore(Byte id, Byte storeId) {
        Staff s = getEntityById(id);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found: " + storeId));
        s.setStore(store);
        return toResponseDTO(staffRepository.save(s));
    }

    @Transactional
    public StaffResponseDTO updatePhone(Byte id, String phone) {
        Staff s = getEntityById(id);
        Address a = s.getAddress();
        a.setPhone(phone);
        a.setLastUpdate(LocalDateTime.now());
        addressRepository.save(a);
        return toResponseDTO(getEntityById(id));
    }

    @Transactional
    public void uploadPicture(Byte id, MultipartFile file) throws IOException {
        Staff staff = getEntityById(id);
        staff.setPicture(file.getBytes());
        staffRepository.save(staff);
    }

    public byte[] getPicture(Byte id) {
        Staff staff = getEntityById(id);
        if (staff.getPicture() == null) {
            throw new RuntimeException("No picture found for staff id: " + id);
        }
        return staff.getPicture();
    }

    @Transactional
    public void deactivateStaff(Byte id) {
        Staff staff = getEntityById(id);
        staff.setActive((byte) 0);
        staffRepository.save(staff);
        
    }

    @Transactional
    public void activateStaff(Byte id) {
        Staff staff = getEntityById(id);
        staff.setActive((byte) 1);
        staffRepository.save(staff);
    }

    public Staff login(String username, String password) {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new RuntimeException("User not found ");
        }
        if (!staff.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        return staff;
    }
}