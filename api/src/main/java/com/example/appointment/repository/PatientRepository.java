package com.example.appointment.repository;

import com.example.appointment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	Patient findByEmail(String emailId);
	Patient findByEmailAndPassword(String emailId, String password);
}

