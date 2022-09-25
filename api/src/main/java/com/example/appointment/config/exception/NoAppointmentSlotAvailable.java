package com.example.appointment.config.exception;

public class NoAppointmentSlotAvailable extends Exception {
    public NoAppointmentSlotAvailable(String msg) {
        super(msg);
    }
}
