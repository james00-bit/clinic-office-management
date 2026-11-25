package com.example.clinic.controller;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinic.model.Office;
import com.example.clinic.repository.OfficeRepository;
@RestController
@RequestMapping("/api/Offices")
@CrossOrigin(origins = "http://localhost:5173")
public class OfficeController {

    @Autowired
    private OfficeRepository officeRepository;

    @PostMapping("/Save")
    public ResponseEntity<Office> saveOffice(@RequestBody Office office) {
        return ResponseEntity.ok(officeRepository.save(office));
    }

    @DeleteMapping("/Delete")
    public ResponseEntity<String> deleteOffice(@RequestParam String name,
@RequestParam String roomNumber) {
        Optional<Office> office = officeRepository.findByNameAndRoomNumber(name, roomNumber);
        if (office.isEmpty()) {
            return ResponseEntity.status(404).body("Office not found");
        }
        try {
            officeRepository.delete(office.get());
            return ResponseEntity.ok("Office deleted");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body("Office is used by a doctor");
        }
    
    }
        @GetMapping("/All")
    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deleteOfficeById(@PathVariable Long id) {
        if (!officeRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Office not found");
        }
        try {
            officeRepository.deleteById(id);
            return ResponseEntity.ok("Office deleted");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body("Office is used by a doctor");
        }
    }
}