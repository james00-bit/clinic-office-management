package com.example.clinic.service;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.AppointmentStatus;
import com.example.clinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AppointmentService(AppointmentRepository repository, PatientService patientService, DoctorService doctorService) {
        this.repository = repository;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public List<Appointment> findAll() { return repository.findAll(); }

    public Appointment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Appointment not found: " + id));
    }

    public List<Appointment> findByDoctor(Long doctorId) { return repository.findByDoctorId(doctorId); }
    public List<Appointment> findByPatient(Long patientId) { return repository.findByPatientId(patientId); }

    public Appointment book(Long patientId, Long doctorId, LocalDateTime date, String reason) {
        Appointment a = new Appointment();
        a.setPatient(patientService.findById(patientId));
        a.setDoctor(doctorService.findById(doctorId));
        a.setAppointmentDate(date);
        a.setReason(reason);
        a.setStatus(AppointmentStatus.SCHEDULED);
        return repository.save(a);
    }

    public Appointment updateStatus(Long id, AppointmentStatus status) {
        Appointment a = findById(id);
        a.setStatus(status);
        return repository.save(a);
    }

    public void delete(Long id) { repository.deleteById(id); }
}
