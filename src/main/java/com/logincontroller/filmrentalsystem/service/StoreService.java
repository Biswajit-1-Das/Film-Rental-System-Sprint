package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.StoreDTO;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    private StoreDTO toDTO(Store store) {
        StoreDTO dto = new StoreDTO();
        dto.setStoreId(store.getStoreId());
        dto.setLastUpdate(store.getLastUpdate());
        // flatten manager staff — avoids Store → Staff → Store circular reference
        if (store.getManagerStaff() != null) {
            dto.setManagerStaffId(store.getManagerStaff().getStaffId());
            dto.setManagerFirstName(store.getManagerStaff().getFirstName());
            dto.setManagerLastName(store.getManagerStaff().getLastName());
        }
        // flatten address → city → country
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

    public Store getEntityById(Integer id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StoreDTO getStoreById(Integer id) {
        return toDTO(getEntityById(id));
    }

    public StoreDTO saveStore(Store store) {
        return toDTO(storeRepository.save(store));
    }

    public void deleteStore(Integer id) {
        storeRepository.deleteById(id);
    }
}