package com.example.clinic.controller;

import com.example.clinic.model.Specialization;
import com.example.clinic.service.SpecializationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {
    private final SpecializationService service;

    public SpecializationController(SpecializationService service) { this.service = service; }

    @GetMapping
    public List<Specialization> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Specialization getById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Specialization create(@RequestBody Specialization specialization) { return service.save(specialization); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
