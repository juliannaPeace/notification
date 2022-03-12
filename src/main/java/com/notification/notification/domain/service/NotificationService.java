package com.notification.notification.domain.service;

import com.notification.notification.LocalDateTimeFactory;
import com.notification.notification.domain.entity.NotificationConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    private final LocalDateTimeFactory localDateTimeFactory;
    private final List<NotificationConfiguration> configurations;

    public NotificationService(LocalDateTimeFactory localDateTimeFactory, List<NotificationConfiguration> configurations) {
        this.localDateTimeFactory = localDateTimeFactory;
        this.configurations = configurations;
    }

    public List<NotificationConfiguration> send(){
        List<NotificationConfiguration> configurationsSend = new ArrayList<>();

        for(NotificationConfiguration configuration: configurations){
            if(configuration.isTimeToSend(localDateTimeFactory)){
                configurationsSend.add(configuration);
            }
        }
        return configurationsSend;
    }
}
