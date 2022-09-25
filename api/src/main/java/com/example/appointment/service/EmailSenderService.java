package com.example.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(SimpleMailMessage msg) {
        //msg.setTo("sunilnobeltraining@gmail.com");
        msg.setSubject("Dr. Appointment Success Notification");
        //msg.setText("Hello World \n Spring Boot Email");
        javaMailSender.send(msg);
        System.out.println("Mail Sent Successfully...");
    }
}
