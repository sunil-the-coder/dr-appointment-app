package com.example.appointment.service;

import com.example.appointment.dto.LoginDto;
import com.example.appointment.entity.Patient;
import com.example.appointment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatient(long id) {
        Patient response = Optional.ofNullable(patientRepository.findById(id)).get().orElse(null);
        return response;
    }

    public boolean registerPatient(Patient patient) {
        Patient existingPatient = Optional.ofNullable(patientRepository.findByEmail(patient.getEmail())).orElse(null);

        if (existingPatient != null) {
            return false;
        }

        patientRepository.save(patient);
        return true;
    }

    public Patient authenticatePatient(LoginDto loginDto) {
        try {
            return Optional.ofNullable(patientRepository.findByEmailAndPassword(loginDto.getEmailId(), loginDto.getPassword())).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
