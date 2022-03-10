package com.notification.notification;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class NotificationService {

    private final LocalDateTimeFactory localDateTimeFactory;

    public NotificationService(LocalDateTimeFactory localDateTimeFactory) {
        this.localDateTimeFactory = localDateTimeFactory;
    }

    public Optional<Configuration> send(TypeUser typeUser){
        if(TypeUser.NOOB.equals(typeUser)){
            if(localDateTimeFactory.now().equals(LocalDate.now().atTime(12,0))) {
                return Optional.of(new Configuration(7, 12, 0,
                        "Você precisa usar mais nosso APP para " +
                                "obter cada vez mais promoções"));
            }

            if(localDateTimeFactory.now().equals(LocalDate.now().atTime(18,0))) {
                return Optional.of(new Configuration(7, 18, 0,
                        "Você precisa usar mais nosso APP para " +
                                "obter cada vez mais promoções"));
            }
        }
        return Optional.empty();
    }
}
