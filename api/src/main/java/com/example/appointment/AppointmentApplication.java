package com.example.appointment;

import com.example.appointment.entity.AppointmentSlot;
import com.example.appointment.repository.AppointmentSlotRepository;
import com.example.appointment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootApplication
@EnableSwagger2
public class AppointmentApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentApplication.class, args);
	}

	@Autowired
	AppointmentSlotRepository appointmentSlotRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Override
	public void run(String... args) throws Exception {
		AppointmentSlot slot1 = new AppointmentSlot();
	//	slot1.setDoctor(doctorRepository.findById(1L).get());
		slot1.setAppointmentDate(LocalDate.now());
		slot1.setStartTime(LocalTime.of(10,00));
		slot1.setEndTime(LocalTime.of(10,30));
		slot1.setStatus("AVAILABLE");
		//appointmentSlotRepository.save(slot1);
	}
}
