package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.dto.RentalResponseDTO;
import com.logincontroller.filmrentalsystem.model.*;
import com.logincontroller.filmrentalsystem.repository.CustomerRepository;
import com.logincontroller.filmrentalsystem.repository.InventoryRepository;
import com.logincontroller.filmrentalsystem.repository.RentalRepository;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final InventoryRepository inventoryRepository;
    private final FilmService filmService;

    private RentalResponseDTO toResponseDTO(Rental r) {
        RentalResponseDTO dto = new RentalResponseDTO();
        dto.setRentalId(r.getRentalId());
        dto.setRentalDate(r.getRentalDate());
        dto.setReturnDate(r.getReturnDate());
        dto.setLastUpdate(r.getLastUpdate());
        if (r.getInventory() != null) {
            dto.setInventoryId(r.getInventory().getInventoryId());
            if (r.getInventory().getFilm() != null) {
                dto.setFilmId(r.getInventory().getFilm().getFilmId());
                dto.setFilmTitle(r.getInventory().getFilm().getTitle());
            }
        }
        if (r.getCustomer() != null) {
            dto.setCustomerId(r.getCustomer().getCustomerId());
            dto.setCustomerName(r.getCustomer().getFirstName() + " " + r.getCustomer().getLastName());
        }
        if (r.getStaff() != null) {
            dto.setStaffId(r.getStaff().getStaffId());
            dto.setStaffName(r.getStaff().getFirstName() + " " + r.getStaff().getLastName());
        }
        return dto;
    }

    @Transactional
    public RentalResponseDTO createRental(Short customerId, Byte staffId,
                                          Integer inventoryId, Rental rental) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setInventory(inventory);

        rental.setRentalDate(LocalDateTime.now());
        rental.setLastUpdate(LocalDateTime.now());

        return toResponseDTO(rentalRepository.save(rental));
    }

    @Transactional(readOnly = true)
    public RentalResponseDTO getRentalById(Integer id) {
        return toResponseDTO(getEntityById(id));
    }

    public Rental getEntityById(Integer id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<RentalResponseDTO> getAllRentals() {
        return rentalRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RentalResponseDTO> getRentalsByCustomer(Short customerId) {
        return rentalRepository.findByCustomerCustomerId(customerId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RentalResponseDTO> getRentalsByStaff(Byte staffId) {
        return rentalRepository.findByStaffStaffId(staffId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RentalResponseDTO> getRentalsByInventory(Integer inventoryId) {
        return rentalRepository.findByInventoryInventoryId(inventoryId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getTopTenFilmsByRentalCount() {
        List<Short> ids = rentalRepository.findTopTenFilmIdsByRentalCount();
        List<FilmResponseDTO> out = new ArrayList<>();
        for (Short id : ids) {
            out.add(filmService.getFilmById(id));
        }
        return out;
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getTopTenFilmsByRentalCountForStore(Byte storeId) {
        List<Short> ids = rentalRepository.findTopTenFilmIdsByRentalCountForStore(storeId);
        List<FilmResponseDTO> out = new ArrayList<>();
        for (Short id : ids) {
            out.add(filmService.getFilmById(id));
        }
        return out;
    }

    @Transactional(readOnly = true)
    public List<RentalResponseDTO> getDueRentalsByStore(Byte storeId) {
        return rentalRepository.findDueRentalsByStoreId(storeId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RentalResponseDTO updateReturnDate(Integer rentalId, LocalDateTime returnDate) {
        Rental existing = getEntityById(rentalId);
        existing.setReturnDate(returnDate);
        existing.setLastUpdate(LocalDateTime.now());
        return toResponseDTO(rentalRepository.save(existing));
    }

    @Transactional
    public RentalResponseDTO updateRental(Integer id, Rental updatedRental,
                                            Short customerId, Byte staffId, Integer inventoryId) {

        Rental existing = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        existing.setCustomer(customer);
        existing.setStaff(staff);
        existing.setInventory(inventory);
        existing.setRentalDate(updatedRental.getRentalDate());
        existing.setReturnDate(updatedRental.getReturnDate());
        existing.setLastUpdate(LocalDateTime.now());

        return toResponseDTO(rentalRepository.save(existing));
    }
}
