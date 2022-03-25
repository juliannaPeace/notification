package com.notification.notification;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class LocalDateTimeFactory {

    public LocalDateTime now(){
        return LocalDateTime.now();
    }

    public DayOfWeek dayOfWeek(){ return LocalDateTime.now().getDayOfWeek();}
}
