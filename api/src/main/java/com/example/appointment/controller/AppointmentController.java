package com.example.appointment.controller;

import com.example.appointment.dto.AppointmentDto;
import com.example.appointment.entity.Appointment;
import com.example.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(path = {"/patient/{patientId}"})
    public List<Appointment> getPatientAppointments(@PathVariable("patientId") long patientId) {
        return appointmentService.getPatientAppointments(patientId);
    }

    @GetMapping(path = {"/doctor/{doctorId}"})
    public List<Appointment> getDoctorAppointments(@PathVariable("doctorId") long doctorId) {
        return appointmentService.getDoctorAppointments(doctorId);
    }

    @PostMapping(path = {"/register"})
    public Appointment registerAppointment(@RequestBody AppointmentDto appointmentDto) {
        return appointmentService.takeAppointment(appointmentDto);
    }

    @DeleteMapping(path = {"/cancel/{appointmentId}"})
    public boolean cancelAppointment(@PathVariable("appointmentId") long appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }
}
