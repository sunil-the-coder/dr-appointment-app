package com.example.appointment.repository;

import com.example.appointment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Doctor findByEmail(String emailId);
	Doctor findByEmailAndPassword(String emailId, String password);
}

