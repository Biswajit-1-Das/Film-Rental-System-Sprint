package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.*;
import com.logincontroller.filmrentalsystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoresService {

    @Autowired
    private StoresRepository storeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AddressRepository addressRepository;

    // ✅ CREATE
    public Store createStore(Integer managerStaffId, Integer addressId, Store store) {

        Staff manager = staffRepository.findById(managerStaffId)
                .orElseThrow(() -> new RuntimeException("Manager staff not found"));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        store.setManagerStaff(manager);
        store.setAddress(address);

        return storeRepository.save(store);
    }

    // ✅ GET BY ID
    public Store getStoreById(Integer id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    // ✅ GET ALL
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    // ✅ GET BY ADDRESS
    public List<Store> getStoresByAddress(Integer addressId) {
        return storeRepository.findByAddressAddressId(addressId);
    }

    // ✅ GET BY MANAGER STAFF
    public List<Store> getStoresByManager(Integer staffId) {
        return storeRepository.findByManagerStaffStaffId(staffId);
    }

    // ✅ UPDATE
    public Store updateStore(Integer id, Store updatedStore,
                             Integer managerStaffId, Integer addressId) {

        Store existing = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Staff manager = staffRepository.findById(managerStaffId)
                .orElseThrow(() -> new RuntimeException("Manager staff not found"));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        existing.setManagerStaff(manager);
        existing.setAddress(address);

        return storeRepository.save(existing);
    }
}