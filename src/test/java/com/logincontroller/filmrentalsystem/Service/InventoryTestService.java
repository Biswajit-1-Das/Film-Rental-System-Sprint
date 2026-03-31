package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.InventoryResponseDTO;
import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.model.Store;
import com.logincontroller.filmrentalsystem.repository.FilmRepository;
import com.logincontroller.filmrentalsystem.repository.InventoryRepository;
import com.logincontroller.filmrentalsystem.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class InventoryTestService {

    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    FilmRepository filmRepository;

    @InjectMocks
    InventoryService inventoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getInventoryById_mapsToDto() {
        Integer id = 1;

        Film film = new Film();
        film.setFilmId((short) 5);
        film.setTitle("Film");

        Inventory inv = new Inventory();
        inv.setInventoryId(id);
        inv.setLastUpdate(LocalDateTime.now());
        inv.setFilm(film);

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inv));

        InventoryResponseDTO dto = inventoryService.getInventoryById(id);

        assertEquals(id, dto.getInventoryId());
        assertEquals((short) 5, dto.getFilmId());
        assertEquals("Film", dto.getFilmTitle());
        verify(inventoryRepository).findById(id);
    }

    @Test
    public void getInventoryByFilmAndStore_returnsDtos() {
        Short filmId = 1;
        Byte storeId = 2;

        Inventory inv1 = new Inventory();
        inv1.setInventoryId(10);
        inv1.setLastUpdate(LocalDateTime.now());

        when(inventoryRepository.findByFilm_FilmIdAndStore_StoreId(filmId, storeId))
                .thenReturn(List.of(inv1));

        List<InventoryResponseDTO> out = inventoryService.getInventoryByFilmAndStore(filmId, storeId);

        assertEquals(1, out.size());
        assertEquals(10, out.get(0).getInventoryId());
        verify(inventoryRepository).findByFilm_FilmIdAndStore_StoreId(filmId, storeId);
    }
}

