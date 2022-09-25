package com.example.appointment.controller;

import com.example.appointment.config.exception.NoAppointmentSlotAvailable;
import com.example.appointment.dto.AppointmentRequestDto;
import com.example.appointment.dto.AppointmentResponseDto;
import com.example.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(path = {"/patient/{patientId}"})
    public List<AppointmentResponseDto> getPatientAppointments(@PathVariable("patientId") long patientId) {
        return appointmentService.getPatientAppointments(patientId);
    }

    @GetMapping(path = {"/doctor/{doctorId}"})
    public List<AppointmentResponseDto> getDoctorAppointments(@PathVariable("doctorId") long doctorId) {
        return appointmentService.getDoctorAppointments(doctorId);
    }

    @PostMapping(path = {"/register"})
    public ResponseEntity<AppointmentResponseDto> registerAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) {
        try {
            return new ResponseEntity(appointmentService.registerAppointment(appointmentRequestDto), HttpStatus.OK);
        }catch(NoAppointmentSlotAvailable e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = {"/cancel/{appointmentId}"})
    public boolean cancelAppointment(@PathVariable("appointmentId") long appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }
}
