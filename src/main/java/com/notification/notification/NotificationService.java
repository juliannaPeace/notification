package com.notification.notification;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    private final LocalDateTimeFactory localDateTimeFactory;

    public NotificationService(LocalDateTimeFactory localDateTimeFactory) {
        this.localDateTimeFactory = localDateTimeFactory;
    }

    public Optional<Configuration> send(TypeUser typeUser){
        if(TypeUser.NOOB.equals(typeUser)){
            var configuration = new NoobConfiguration();
            if(configuration.isTimeToSend(localDateTimeFactory)){
                return Optional.of(configuration);
            }
        }

        if(TypeUser.INTERMEDIARY.equals(typeUser)){
            var configuration = new IntermediaryConfiguration();
            if(configuration.isTimeToSend(localDateTimeFactory)){
                return Optional.of(configuration);
            }
        }
        return Optional.empty();
    }
}
