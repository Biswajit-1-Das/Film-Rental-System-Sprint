package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.CustomerResponseDTO;
import com.logincontroller.filmrentalsystem.dto.StaffResponseDTO;
import com.logincontroller.filmrentalsystem.dto.StoreResponseDTO;
import com.logincontroller.filmrentalsystem.model.Address;
import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.repository.AddressRepository;
import com.logincontroller.filmrentalsystem.repository.CustomerRepository;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import com.logincontroller.filmrentalsystem.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final StaffRepository staffRepository;
    private final CustomerRepository customerRepository;
    private final StaffService staffService;

    private StoreResponseDTO toResponseDTO(Store store) {
        StoreResponseDTO dto = new StoreResponseDTO();
        dto.setStoreId(store.getStoreId());
        dto.setLastUpdate(store.getLastUpdate());

        if (store.getManagerStaff() != null) {
            dto.setManagerStaffId(store.getManagerStaff().getStaffId());
            dto.setManagerFirstName(store.getManagerStaff().getFirstName());
            dto.setManagerLastName(store.getManagerStaff().getLastName());
        }

        if (store.getAddress() != null) {
            dto.setAddressId(store.getAddress().getAddressId());
            dto.setAddressLine(store.getAddress().getAddress());

            if (store.getAddress().getCity() != null) {
                dto.setCity(store.getAddress().getCity().getCity());

                if (store.getAddress().getCity().getCountry() != null) {
                    dto.setCountry(store.getAddress().getCity().getCountry().getCountry());
                }
            }
        }

        return dto;
    }

    private CustomerResponseDTO customerToResponse(Customer c) {
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setCustomerId(c.getCustomerId());
        dto.setFirstName(c.getFirstName());
        dto.setLastName(c.getLastName());
        dto.setEmail(c.getEmail());
        dto.setActive(c.getActive() != null && c.getActive() != 0);
        dto.setCreateDate(c.getCreateDate());
        dto.setLastUpdate(c.getLastUpdate());

        if (c.getStore() != null) {
            dto.setStoreId(c.getStore().getStoreId());
        }

        if (c.getAddress() != null) {
            dto.setAddressId(c.getAddress().getAddressId());
            dto.setAddressLine(c.getAddress().getAddress());
            dto.setPostalCode(c.getAddress().getPostalCode());
            dto.setPhone(c.getAddress().getPhone());

            if (c.getAddress().getCity() != null) {
                dto.setCity(c.getAddress().getCity().getCity());

                if (c.getAddress().getCity().getCountry() != null) {
                    dto.setCountry(c.getAddress().getCity().getCountry().getCountry());
                }
            }
        }

        return dto;
    }

    public Store getEntityById(Byte id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDTO> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        List<StoreResponseDTO> result = new ArrayList<>();

        for (Store s : stores) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public StoreResponseDTO getStoreById(Byte id) {
        return toResponseDTO(getEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDTO> getStoresByAddress(Short addressId) {
        List<Store> stores = storeRepository.findByAddressAddressId(addressId);
        List<StoreResponseDTO> result = new ArrayList<>();

        for (Store s : stores) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDTO> getStoresByManagerStaff(Byte staffId) {
        List<Store> stores = storeRepository.findByManagerStaffStaffId(staffId);
        List<StoreResponseDTO> result = new ArrayList<>();

        for (Store s : stores) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDTO> getStoresByCity(String city) {
        List<Store> stores = storeRepository.findByAddress_City_City(city);
        List<StoreResponseDTO> result = new ArrayList<>();

        for (Store s : stores) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDTO> getStoresByCountry(String country) {
        List<Store> stores = storeRepository.findByAddress_City_Country_Country(country);
        List<StoreResponseDTO> result = new ArrayList<>();

        for (Store s : stores) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<StoreResponseDTO> getStoresByPhone(String phone) {
        List<Store> stores = storeRepository.findByAddress_Phone(phone);
        List<StoreResponseDTO> result = new ArrayList<>();

        for (Store s : stores) {
            result.add(toResponseDTO(s));
        }

        return result;
    }

    @Transactional
    public StoreResponseDTO saveStore(Store store) {
        return toResponseDTO(storeRepository.save(store));
    }

    @Transactional
    public StoreResponseDTO linkStoreAddress(Byte storeId, Short addressId) {
        Store store = getEntityById(storeId);
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found: " + addressId));

        store.setAddress(address);
        return toResponseDTO(storeRepository.save(store));
    }

    @Transactional
    public StoreResponseDTO updateStorePhone(Byte storeId, String phone) {
        Store store = getEntityById(storeId);
        Address a = store.getAddress();
        a.setPhone(phone);
        a.setLastUpdate(LocalDateTime.now());
        addressRepository.save(a);

        return toResponseDTO(getEntityById(storeId));
    }

    @Transactional
    public StoreResponseDTO setStoreManager(Byte storeId, Byte managerStaffId) {
        Store store = getEntityById(storeId);
        Staff manager = staffRepository.findById(managerStaffId)
                .orElseThrow(() -> new RuntimeException("Staff not found: " + managerStaffId));

        store.setManagerStaff(manager);
        return toResponseDTO(storeRepository.save(store));
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> getStaffForStore(Byte storeId) {
        List<Staff> staffList = staffRepository.findByStoreStoreId(storeId);
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : staffList) {
            result.add(staffService.mapStaff(s));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getCustomersForStore(Byte storeId) {
        List<Customer> customers = customerRepository.findByStoreStoreId(storeId);
        List<CustomerResponseDTO> result = new ArrayList<>();

        for (Customer c : customers) {
            result.add(customerToResponse(c));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public StaffResponseDTO getManagerForStore(Byte storeId) {
        Store store = getEntityById(storeId);
        return staffService.mapStaff(store.getManagerStaff());
    }

    @Transactional(readOnly = true)
    public List<StaffResponseDTO> getAllStoreManagers() {
        List<Staff> staffList = storeRepository.findDistinctStoreManagers();
        List<StaffResponseDTO> result = new ArrayList<>();

        for (Staff s : staffList) {
            result.add(staffService.mapStaff(s));
        }

        return result;
    }

    public void deleteStore(Byte id) {
        storeRepository.deleteById(id);
    }
}