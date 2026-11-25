package com.example.clinic.controller;

import com.example.clinic.model.Patient;
import com.example.clinic.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService service;

    public PatientController(PatientService service) { this.service = service; }

    @GetMapping
    public List<Patient> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient create(@RequestBody Patient patient) { return service.save(patient); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
