package com.example.appointment.service;

import com.example.appointment.config.exception.NoAppointmentSlotAvailable;
import com.example.appointment.dto.AppointmentRequestDto;
import com.example.appointment.dto.AppointmentResponseDto;
import com.example.appointment.entity.Appointment;
import com.example.appointment.entity.AppointmentSlot;
import com.example.appointment.entity.Doctor;
import com.example.appointment.entity.Patient;
import com.example.appointment.mapper.EntityToDtoMapper;
import com.example.appointment.repository.AppointmentRepository;
import com.example.appointment.repository.AppointmentSlotRepository;
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

    public List<AppointmentResponseDto> getDoctorAppointments(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctor(doctorService.getDoctor(doctorId));
        List<AppointmentResponseDto> responseDtoList = EntityToDtoMapper.mapToDto(appointments);
        return responseDtoList;
    }

    public List<AppointmentResponseDto> getPatientAppointments(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatient(patientService.getPatient(patientId));
        List<AppointmentResponseDto> responseDtoList = EntityToDtoMapper.mapToDto(appointments);
        return responseDtoList;
    }

    @Transactional
    public AppointmentResponseDto registerAppointment(AppointmentRequestDto appointmentRequestDto) throws NoAppointmentSlotAvailable {
        Appointment appointment  = new Appointment();
        appointment.setAppointmentDate(appointmentRequestDto.getAppointmentDate());
        String[] startTime = appointmentRequestDto.getStartTime().split(":");
        String[] endTime = appointmentRequestDto.getEndTime().split(":");

        LocalTime localStartTime = LocalTime.of(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]));
        appointment.setStartTime(localStartTime);
        LocalTime localEndTime = LocalTime.of(Integer.parseInt(endTime[0]), Integer.parseInt(endTime[1]));
        appointment.setEndTime(localEndTime);

        Doctor doctor = doctorService.getDoctor(appointmentRequestDto.getDoctorId());
        appointment.setDoctor(doctor);
        Patient patient = patientService.getPatient(appointmentRequestDto.getPatientId());
        appointment.setPatient(patient);
        appointment.setStatus("SCHEDULLED");

        //Make new entry as scheduled appointment
        appointmentRepository.save(appointment);


        //Update slot as booked.
        AppointmentSlot appointmentSlot = appointmentSlotRepository.findByDoctorAndAppointmentDateAndStartTimeAndEndTime(doctor,
                appointmentRequestDto.getAppointmentDate(), localStartTime, localEndTime);
        appointmentSlot.setStatus("BOOKED");

        if(appointmentSlot != null) {
            appointmentSlotRepository.save(appointmentSlot);

            //Send email to Dr and Patient - Time consuming task
            new Thread(() -> {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setText("Doctor's Appointment Booked");
                msg.setTo(patient.getEmail(), doctor.getEmail());
                msg.setText("Appointment Details - \n  Doctor Name - " + doctor.getFullName() + "\n Patient Name - " + patient.getFullName() + "\n Date and Time - " + appointment.getAppointmentDate() + " " + appointment.getStartTime() + "-" + appointment.getEndTime());
                emailSenderService.sendEmail(msg);
            }).start();

        }else
            throw new NoAppointmentSlotAvailable("Slot Not Available");

       return EntityToDtoMapper.mapToDto(appointment);

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
