package com.example.clinic.controller;

import com.example.clinic.model.Appointment;
import com.example.clinic.model.AppointmentStatus;
import com.example.clinic.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentService service;

    public AppointmentController(AppointmentService service) { this.service = service; }

    public record BookingRequest(Long patientId, Long doctorId, LocalDateTime appointmentDate, String reason) {}

    @GetMapping
    public List<Appointment> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) { return service.findById(id); }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> byDoctor(@PathVariable Long doctorId) { return service.findByDoctor(doctorId); }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> byPatient(@PathVariable Long patientId) { return service.findByPatient(patientId); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment book(@RequestBody BookingRequest r) {
        return service.book(r.patientId(), r.doctorId(), r.appointmentDate(), r.reason());
    }

    @PatchMapping("/{id}/status")
    public Appointment updateStatus(@PathVariable Long id, @RequestParam AppointmentStatus status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
