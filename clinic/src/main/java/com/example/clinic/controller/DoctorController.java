package com.example.clinic.controller;

import com.example.clinic.model.Doctor;
import com.example.clinic.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService service) { this.service = service; }

    @GetMapping
    public List<Doctor> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) { return service.findById(id); }

    // body includes the nested office: {"fullName":"Dr. X","office":{"roomNumber":"101","floor":1}}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor create(@RequestBody Doctor doctor) { return service.save(doctor); }

    @PutMapping("/{id}/specializations")
    public Doctor assignSpecializations(@PathVariable Long id, @RequestBody List<Long> specializationIds) {
        return service.assignSpecializations(id, specializationIds);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
