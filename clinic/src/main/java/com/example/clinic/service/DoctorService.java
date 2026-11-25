package com.example.clinic.service;

import com.example.clinic.model.Doctor;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.SpecializationRepository;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository repository;
    private final SpecializationRepository specializationRepository;

    public DoctorService(DoctorRepository repository, SpecializationRepository specializationRepository) {
        this.repository = repository;
        this.specializationRepository = specializationRepository;
    }

    public List<Doctor> findAll() { return repository.findAll(); }

    public Doctor findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found: " + id));
    }

    public Doctor save(Doctor doctor) { return repository.save(doctor); }

    public Doctor assignSpecializations(Long doctorId, List<Long> specializationIds) {
        Doctor doctor = findById(doctorId);
        doctor.setSpecializations(new HashSet<>(specializationRepository.findAllById(specializationIds)));
        return repository.save(doctor);
    }

    public void delete(Long id) { repository.deleteById(id); }
}
