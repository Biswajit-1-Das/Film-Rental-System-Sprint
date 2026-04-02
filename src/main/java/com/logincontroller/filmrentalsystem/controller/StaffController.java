package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.AddressResponseDTO;
import com.logincontroller.filmrentalsystem.dto.StaffResponseDTO;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.service.AddressService;
import com.logincontroller.filmrentalsystem.service.StaffService;
import com.logincontroller.filmrentalsystem.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://10.191.27.14:9090")
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;
    private final AddressService addressService;
    private final StoreService storeService;


    @GetMapping
    public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<StaffResponseDTO>> byLastName(@PathVariable String ln) {
        return ResponseEntity.ok(staffService.searchByLastName(ln));
    }

    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<StaffResponseDTO>> byFirstName(@PathVariable String fn) {
        return ResponseEntity.ok(staffService.searchByFirstName(fn));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StaffResponseDTO> byEmail(@PathVariable String email) {
        return ResponseEntity.ok(staffService.getStaffByEmail(email));
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<AddressResponseDTO> staffAddress(@PathVariable Byte id) {
        return ResponseEntity.ok(staffService.getAddressForStaff(id));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<StaffResponseDTO>> byCity(@PathVariable String city) {
        return ResponseEntity.ok(staffService.getStaffByCity(city));
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<StaffResponseDTO>> byCountry(@PathVariable String country) {
        return ResponseEntity.ok(staffService.getStaffByCountry(country));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<StaffResponseDTO>> byPhone(@PathVariable String phone) {
        return ResponseEntity.ok(staffService.getStaffByPhone(phone));
    }

    @GetMapping("/active")
    public ResponseEntity<List<StaffResponseDTO>> getActiveStaff() {
        return ResponseEntity.ok(staffService.getActiveStaff());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<StaffResponseDTO>> getStaffByStore(@PathVariable Byte storeId) {
        return ResponseEntity.ok(staffService.getStaffByStore(storeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> getStaffById(@PathVariable Byte id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @GetMapping(value = "/{id}/picture", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPicture(@PathVariable Byte id) {
        return ResponseEntity.ok(staffService.getPicture(id));
    }

    @PostMapping("/post")
    public ResponseEntity<StaffResponseDTO> createStaff(@RequestBody Staff staff) {
        if (staff.getStore() != null && staff.getStore().getStoreId() != null) {
            staff.setStore(storeService.getEntityById(staff.getStore().getStoreId()));
        }
        if (staff.getAddress() != null && staff.getAddress().getAddressId() != null) {
            staff.setAddress(addressService.getEntityById(staff.getAddress().getAddressId()));
        }
        return ResponseEntity.ok(staffService.createStaff(staff));
    }

    @PostMapping("/{id}/picture")
    public ResponseEntity<String> uploadPicture(@PathVariable Byte id,
                                                @RequestParam MultipartFile file)
            throws IOException {
        staffService.uploadPicture(id, file);
        return ResponseEntity.ok("Picture uploaded successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> updateStaff(@PathVariable Byte id, @RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.updateStaff(id, staff));
    }

    @PatchMapping("/{id}/firstname")
    public ResponseEntity<StaffResponseDTO> updateFirstName(
            @PathVariable Byte id,
            @RequestBody Map<String, String> body) {

        return ResponseEntity.ok(
                staffService.updateFirstName(id, body.get("firstName"))
        );
    }

    @PatchMapping("/{id}/lastname")
    public ResponseEntity<StaffResponseDTO> updateLastName(
            @PathVariable Byte id,
            @RequestBody Map<String, String> body) {

        return ResponseEntity.ok(
                staffService.updateLastName(id, body.get("lastName"))
        );
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<StaffResponseDTO> updateEmail(
            @PathVariable Byte id,
            @RequestBody Map<String, String> body) {

        return ResponseEntity.ok(
                staffService.updateEmail(id, body.get("email"))
        );
    }

    @PatchMapping("/{id}/store")
    public ResponseEntity<StaffResponseDTO> updateStore(
            @PathVariable Byte id,
            @RequestBody Map<String, Object> body) {

        Byte storeId = Byte.valueOf(body.get("storeId").toString());

        return ResponseEntity.ok(
                staffService.updateStore(id, storeId)
        );
    }

    @PatchMapping("/{id}/phone")
    public ResponseEntity<StaffResponseDTO> updatePhone(
            @PathVariable Byte id,
            @RequestBody Map<String, String> body) {

        return ResponseEntity.ok(
                staffService.updatePhone(id, body.get("phone"))
        );
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateStaff(@PathVariable Byte id) {
        staffService.deactivateStaff(id);
        return ResponseEntity.ok("Staff deactivated successfully");
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateStaff(@PathVariable Byte id) {
        staffService.activateStaff(id);
        return ResponseEntity.ok("Staff activated successfully");
    }


}
