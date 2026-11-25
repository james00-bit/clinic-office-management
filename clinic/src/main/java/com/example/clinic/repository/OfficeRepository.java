package com.example.clinic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clinic.model.Office;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    Optional<Office> findByNameAndRoomNumber(String name, String roomNumber);
}