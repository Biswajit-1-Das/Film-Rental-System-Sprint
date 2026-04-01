package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.AddressResponseDTO;
import com.logincontroller.filmrentalsystem.dto.StaffResponseDTO;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins="http://10.191.27.14:9090")
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @PostMapping("/post")
    public ResponseEntity<StaffResponseDTO> createStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.createStaff(staff));
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

    @PutMapping("/update/fn/{id}")
    public ResponseEntity<StaffResponseDTO> updateFirstName(
            @PathVariable Byte id,
            @RequestParam String firstName) {
        return ResponseEntity.ok(staffService.updateFirstName(id, firstName));
    }

    @PutMapping("/update/ln/{id}")
    public ResponseEntity<StaffResponseDTO> updateLastName(
            @PathVariable Byte id,
            @RequestParam String lastName) {
        return ResponseEntity.ok(staffService.updateLastName(id, lastName));
    }

    @PutMapping("/update/email/{id}")
    public ResponseEntity<StaffResponseDTO> updateEmail(
            @PathVariable Byte id,
            @RequestParam String email) {
        return ResponseEntity.ok(staffService.updateEmail(id, email));
    }

    @PutMapping("/update/store/{id}")
    public ResponseEntity<StaffResponseDTO> updateStore(
            @PathVariable Byte id,
            @RequestParam Byte storeId) {
        return ResponseEntity.ok(staffService.updateStore(id, storeId));
    }

    @PutMapping("/update/phone/{id}")
    public ResponseEntity<StaffResponseDTO> updatePhone(
            @PathVariable Byte id,
            @RequestParam String phone) {
        return ResponseEntity.ok(staffService.updatePhone(id, phone));
    }

    @GetMapping
    public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
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

    @PutMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> updateStaff(@PathVariable Byte id,
                                                        @RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.updateStaff(id, staff));
    }

    @PostMapping("/{id}/picture")
    public ResponseEntity<String> uploadPicture(@PathVariable Byte id,
                                                @RequestParam MultipartFile file)
            throws IOException {
        staffService.uploadPicture(id, file);
        return ResponseEntity.ok("Picture uploaded successfully");
    }

    @GetMapping(value = "/{id}/picture", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPicture(@PathVariable Byte id) {
        return ResponseEntity.ok(staffService.getPicture(id));
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
