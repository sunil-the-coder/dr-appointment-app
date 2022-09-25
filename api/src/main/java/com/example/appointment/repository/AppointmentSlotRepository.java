package com.example.appointment.repository;

import com.example.appointment.entity.AppointmentSlot;
import com.example.appointment.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentSlotRepository extends JpaRepository<AppointmentSlot, Long> {
	List<AppointmentSlot> findByDoctor(Long id);
	AppointmentSlot findByDoctorAndAppointmentDateAndStartTimeAndEndTime(Doctor doctor, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime);
}

