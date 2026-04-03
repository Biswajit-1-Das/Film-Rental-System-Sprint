package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.InventoryResponseDTO;
import com.logincontroller.filmrentalsystem.exception.ResourceNotFoundException;
import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.repository.FilmRepository;
import com.logincontroller.filmrentalsystem.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final FilmRepository filmRepository;

    private InventoryResponseDTO toResponseDTO(Inventory inv) {
//        InventoryResponseDTO dto = new InventoryResponseDTO();
//        dto.setInventoryId(inv.getInventoryId());
//        dto.setLastUpdate(inv.getLastUpdate());
//        if (inv.getFilm() != null) {
//            dto.setFilmId(inv.getFilm().getFilmId());
//            dto.setFilmTitle(inv.getFilm().getTitle());
//        }
//        if (inv.getStore() != null) {
//            dto.setStoreId(inv.getStore().getStoreId());
//        }
//        return dto;
        InventoryResponseDTO dto = new InventoryResponseDTO();
        dto.setInventoryId(inv.getInventoryId());
        dto.setLastUpdate(inv.getLastUpdate());
        if(inv.getFilm()!=null)
        {
            dto.setFilmId(inv.getFilm().getFilmId());
            dto.setFilmTitle(inv.getFilm().getTitle());
        }
        if(inv.getStore()!=null)
        {
            dto.setStoreId(inv.getStore().getStoreId());
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getAllInventory() {
        return inventoryRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InventoryResponseDTO getInventoryById(Integer id) {
        Inventory inv = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found: " + id));
        return toResponseDTO(inv);
    }

    @Transactional
    public InventoryResponseDTO saveInventory(Inventory inventory) {
        return toResponseDTO(inventoryRepository.save(inventory));
    }

    public void deleteInventory(Integer id) {
        inventoryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getInventoryByFilm(Short filmId) {
//        return inventoryRepository.findByFilm_FilmId(filmId)
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
        List<Inventory> inventory = inventoryRepository.findByFilm_FilmId(filmId);
        List<InventoryResponseDTO> ivr = new ArrayList<>();
        for(Inventory i : inventory)
        {
            ivr.add(toResponseDTO(i));
        }
        return ivr;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getInventoryByStore(Byte storeId) {
//        return inventoryRepository.findByStore_StoreId(storeId)
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());

        List<Inventory> inventory = inventoryRepository.findByStore_StoreId(storeId);
        List<InventoryResponseDTO> result = new ArrayList<>();
        for(Inventory i : inventory)
        {
            result.add(toResponseDTO(i));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getInventoryByFilmAndStore(Short filmId, Byte storeId) {
//        return inventoryRepository.findByFilm_FilmIdAndStore_StoreId(filmId, storeId)
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
//    }
        List<Inventory> inventory = inventoryRepository.findByFilm_FilmIdAndStore_StoreId(filmId, storeId);
        List<InventoryResponseDTO> result = new ArrayList<>();
        for (Inventory i : inventory) {
            result.add(toResponseDTO(i));
        }
        return result;
    }
}
