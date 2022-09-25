package com.example.appointment.controller;

import com.example.appointment.dto.LoginDto;
import com.example.appointment.entity.Patient;
import com.example.appointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping(path = {""})
    public List<Patient> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping(path = {"/{id}"})
    public Patient getPatient(@PathVariable("id") long id) {
        Patient patient = patientService.getPatient(id);
        return patient;
    }

    @PostMapping(path = {"/register"})
    public boolean registerPatient(@RequestBody Patient patient) {
        boolean isRegistered = patientService.registerPatient(patient);
        return isRegistered;
    }

    @PostMapping(path = {"/login"})
    public Patient authenticatePatient(@RequestBody LoginDto loginDto) {
        return patientService.authenticatePatient(loginDto);
    }

}
