package com.logincontroller.filmrentalsystem.service;

import org.springframework.stereotype.Service;

import com.logincontroller.filmrentalsystem.model.Stores;
import com.logincontroller.filmrentalsystem.repository.StoresRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoresService {

    private final StoresRepository storesRepository;

    public StoresService(StoresRepository storesRepository) {
        this.storesRepository = storesRepository;
    }

    public List<Stores> getAllStores() {
        return storesRepository.findAll();
    }

    public Stores getStoreById(Integer id) {
        return storesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    public Stores createStore(Stores store) {
        store.setLastUpdate(LocalDateTime.now());
        return storesRepository.save(store);
    }

    public Stores updateStore(Integer id, Stores storeDetails) {
        Stores existingStore = getStoreById(id);
        
        existingStore.setManagerStaffId(storeDetails.getManagerStaffId());
        existingStore.setAddressId(storeDetails.getAddressId());
        existingStore.setLastUpdate(LocalDateTime.now());
        
        return storesRepository.save(existingStore);
    }

    public Stores patchStore(Integer id, Stores storeUpdates) {
        Stores existingStore = getStoreById(id);
        
        if (storeUpdates.getManagerStaffId() != null) {
            existingStore.setManagerStaffId(storeUpdates.getManagerStaffId());
        }
        if (storeUpdates.getAddressId() != null) {
            existingStore.setAddressId(storeUpdates.getAddressId());
        }
        
        existingStore.setLastUpdate(LocalDateTime.now());
        return storesRepository.save(existingStore);
    }

    public void deleteStore(Integer id) {
        storesRepository.deleteById(id);
    }
}