package com.example.appointment.mapper;

import com.example.appointment.dto.AppointmentResponseDto;
import com.example.appointment.entity.Appointment;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToDtoMapper {

    public static List<AppointmentResponseDto> mapToDto(List<Appointment> appointments) {
        return appointments.stream().map( appointment ->  mapToDto(appointment)).collect(Collectors.toList());
    }

    public static AppointmentResponseDto mapToDto(Appointment appointment) {
            AppointmentResponseDto dto = new AppointmentResponseDto();
            dto.setDoctorName(appointment.getDoctor().getFullName());
            dto.setPatientName(appointment.getPatient().getFullName());
            dto.setAppointmentDate(appointment.getAppointmentDate());
            dto.setStartTime(appointment.getStartTime());
            dto.setEndTime(appointment.getEndTime());
            dto.setId(appointment.getId());
            dto.setStatus(appointment.getStatus());
            return dto;
    }

}
