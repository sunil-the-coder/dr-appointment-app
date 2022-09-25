package com.example.appointment.controller;

import com.example.appointment.dto.LoginDto;
import com.example.appointment.entity.Doctor;
import com.example.appointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/doctor")
public class DoctorController {

    @Autowired
    private DoctorService DoctorService;

    @GetMapping(path = {""})
    public List<Doctor> getDoctors() {
        return DoctorService.getAllDoctors();
    }

    @GetMapping(path = {"/{id}"})
    public Doctor getDoctor(@PathVariable("id") long id) {
        Doctor Doctor = DoctorService.getDoctor(id);
        return Doctor;
    }
    
    @PostMapping(path = {"/register"})
    public boolean registerDoctor(@RequestBody Doctor Doctor) {
        boolean isRegistered = DoctorService.registerDoctor(Doctor);
        return isRegistered;
    }

    @PostMapping(path = {"/login"})
    public Doctor authenticateDoctor(@RequestBody LoginDto loginDto) {
        return DoctorService.DoctorLogin(loginDto);
    }

}
