package com.example.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    // 1-1 owning side: FK office_id lives in doctors; unique + not null = "exactly one office"
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "office_id", nullable = false, unique = true)
    private Office office;

    // N-N owning side: join table doctor_specializations(doctor_id, specialization_id)
    @ManyToMany
    @JoinTable(name = "doctor_specializations",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private Set<Specialization> specializations = new HashSet<>();

    // 1-N inverse side
    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor() {}

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Office getOffice() { return office; }
    public void setOffice(Office office) { this.office = office; }
    public Set<Specialization> getSpecializations() { return specializations; }
    public void setSpecializations(Set<Specialization> specializations) { this.specializations = specializations; }
    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}
