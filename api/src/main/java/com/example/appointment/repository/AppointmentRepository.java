package com.example.appointment.repository;

import com.example.appointment.entity.Appointment;
import com.example.appointment.entity.Doctor;
import com.example.appointment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByDoctor(Doctor doctor);
	List<Appointment> findByPatient(Patient patient);
}

