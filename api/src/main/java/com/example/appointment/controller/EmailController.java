package com.example.appointment.controller;

import com.example.appointment.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/email")
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping(path = {"/testSendEmail"})
    public boolean sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setText("Hello From Dr. Application");
        msg.setTo("sunilnobeltraining@gmail.com");
        emailSenderService.sendEmail(msg);
        return true;
    }

}
