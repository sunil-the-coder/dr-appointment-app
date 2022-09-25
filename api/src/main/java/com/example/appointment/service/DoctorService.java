package com.example.appointment.service;

import com.example.appointment.dto.LoginDto;
import com.example.appointment.entity.Doctor;
import com.example.appointment.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctor(long id) {
        Doctor response = Optional.ofNullable(doctorRepository.findById(id)).get().orElse(null);
        return response;
    }

    public boolean registerDoctor(Doctor Doctor) {
        Doctor existingDoctor = Optional.ofNullable(doctorRepository.findByEmail(Doctor.getEmail())).orElse(null);

        if (existingDoctor != null) {
            return false;
        }

        doctorRepository.save(Doctor);
        return true;
    }

    public Doctor loginDoctor(LoginDto loginDto) {
        try {
            return Optional.ofNullable(doctorRepository.findByEmailAndPassword(loginDto.getEmailId(), loginDto.getPassword())).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
