package com.example.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentDto {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private LocalDate appointmentDate;
    private String startTime;
    private String endTime; //ideal to use LocalTime here but due to swagger parsign issue, we are using string here. ( "10:30" -> object formed at service layer )
}
