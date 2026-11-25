package com.example.clinic.service;

import com.example.clinic.model.Specialization;
import com.example.clinic.repository.SpecializationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecializationService {
    private final SpecializationRepository repository;

    public SpecializationService(SpecializationRepository repository) { this.repository = repository; }

    public List<Specialization> findAll() { return repository.findAll(); }

    public Specialization findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found: " + id));
    }

    public Specialization save(Specialization specialization) { return repository.save(specialization); }

    public void delete(Long id) { repository.deleteById(id); }
}
