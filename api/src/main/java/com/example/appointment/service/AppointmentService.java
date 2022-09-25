package com.example.appointment.service;

import com.example.appointment.dto.AppointmentDto;
import com.example.appointment.entity.Appointment;
import com.example.appointment.entity.AppointmentSlot;
import com.example.appointment.entity.Doctor;
import com.example.appointment.entity.Patient;
import com.example.appointment.repository.AppointmentRepository;
import com.example.appointment.repository.AppointmentSlotRepository;
import com.example.appointment.repository.DoctorRepository;
import com.example.appointment.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    DoctorService doctorService;

    @Autowired
    PatientService patientService;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    AppointmentSlotRepository appointmentSlotRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getDoctorAppointments(Long doctorId) {
        return appointmentRepository.findByDoctor(doctorService.getDoctor(doctorId));
    }

    public List<Appointment> getPatientAppointments(Long patientId) {
        return appointmentRepository.findByPatient(patientService.getPatient(patientId));
    }

    @Transactional
    public Appointment takeAppointment(AppointmentDto appointmentDto) {
        Appointment appointment  = new Appointment();
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        String[] startTime = appointmentDto.getStartTime().split(":");
        String[] endTime = appointmentDto.getEndTime().split(":");

        LocalTime localStartTime = LocalTime.of(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]));
        appointment.setStartTime(localStartTime);
        LocalTime localEndTime = LocalTime.of(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]));
        appointment.setEndTime(localEndTime);

        Doctor doctor = doctorService.getDoctor(appointmentDto.getDoctorId());
        appointment.setDoctor(doctor);
        Patient patient = patientService.getPatient(appointmentDto.getPatientId());
        appointment.setPatient(patient);
        appointment.setStatus("SCHEDULLED");
        appointmentRepository.save(appointment);


        //Update slot as booked.
        AppointmentSlot appointmentSlot = appointmentSlotRepository.findByDoctorAndAppointmentDateAndStartTimeAndEndTime(doctor,
                appointmentDto.getAppointmentDate(), localStartTime, localEndTime);
        appointmentSlot.setStatus("BOOKED");
        appointmentSlotRepository.save(appointmentSlot);

        //Send email to Dr and Patient - Time consuming task
        new Thread(() -> {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setText("Doctor's Appointment Booked");
            msg.setTo(patient.getEmail(), doctor.getEmail());
            msg.setText("Appointment Details - \n  Doctor Name - "+doctor.getFullName()+"\n Patient Name - "+patient.getFullName()+"\n Date and Time - "+appointment.getAppointmentDate()+" "+appointment.getStartTime()+"-"+appointment.getEndTime());
            emailSenderService.sendEmail(msg);
        }).start();


        //Just alternative - ideally to use DTO object here.
        appointment.getDoctor().setPassword("");

        return appointment;
    }

    public boolean cancelAppointment(Long appointmentId) {
        Appointment appointment = Optional.of(appointmentRepository.findById(appointmentId).get()).orElse(null);
        if (appointment != null) {
            appointment.setStatus("CANCELLED");
            appointmentRepository.save(appointment);
            return true;
        }
        return false;
    }

}
