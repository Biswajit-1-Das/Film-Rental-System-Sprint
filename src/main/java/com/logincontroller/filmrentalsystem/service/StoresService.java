package com.logincontroller.filmrentalsystem.service;

import org.springframework.stereotype.Service;

import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.repository.StoresRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoresService {

    private final StoresRepository storesRepository;

    public StoresService(StoresRepository storesRepository) {
        this.storesRepository = storesRepository;
    }

    public List<Store> getAllStores() {
        return storesRepository.findAll();
    }

    public Store getStoreById(Integer id) {
        return storesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    public Store createStore(Store store) {
        store.setLastUpdate(LocalDateTime.now());
        return storesRepository.save(store);
    }

    public Store updateStore(Integer id, Store storeDetails) {
        Store existingStore = getStoreById(id);
        
        existingStore.setManagerStaff(storeDetails.getManagerStaff());
        existingStore.setAddress(storeDetails.getAddress());
        existingStore.setLastUpdate(LocalDateTime.now());
        
        return storesRepository.save(existingStore);
    }

    public Store patchStore(Integer id, Store storeUpdates) {
        Store existingStore = getStoreById(id);
        
        if (storeUpdates.getManagerStaff() != null) {
            existingStore.setManagerStaff(storeUpdates.getManagerStaff());
        }
        if (storeUpdates.getAddress() != null) {
            existingStore.setAddress(storeUpdates.getAddress());
        }
        
        existingStore.setLastUpdate(LocalDateTime.now());
        return storesRepository.save(existingStore);
    }

    public void deleteStore(Integer id) {
        storesRepository.deleteById(id);
    }
}