package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.StaffDTO;
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
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/active")
    public ResponseEntity<List<StaffDTO>> getActiveStaff() {
        return ResponseEntity.ok(staffService.getActiveStaff());
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<StaffDTO>> getStaffByStore(@PathVariable Integer storeId) {
        return ResponseEntity.ok(staffService.getStaffByStore(storeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable Integer id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @PostMapping
    public ResponseEntity<StaffDTO> createStaff(@RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.createStaff(staff));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable Integer id,
                                                @RequestBody Staff staff) {
        return ResponseEntity.ok(staffService.updateStaff(id, staff));
    }

    @PostMapping("/{id}/picture")
    public ResponseEntity<String> uploadPicture(@PathVariable Integer id,
                                                @RequestParam MultipartFile file)
            throws IOException {
        staffService.uploadPicture(id, file);
        return ResponseEntity.ok("Picture uploaded successfully");
    }

    @GetMapping(value = "/{id}/picture", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPicture(@PathVariable Integer id) {
        return ResponseEntity.ok(staffService.getPicture(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateStaff(@PathVariable Integer id) {
        staffService.deactivateStaff(id);
        return ResponseEntity.ok("Staff deactivated successfully");
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<String> activateStaff(@PathVariable Integer id) {
        staffService.activateStaff(id);
        return ResponseEntity.ok("Staff activated successfully");
    }
}