package com.example.appointment.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStarter  {

    @EventListener
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("started");

        System.out.println("start completed");
    }
}
