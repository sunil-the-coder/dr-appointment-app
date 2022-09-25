package com.example.appointment.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment_slot")
@Getter
@Setter
public class AppointmentSlot implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status; //BOOKED,AVAILABLE

}
