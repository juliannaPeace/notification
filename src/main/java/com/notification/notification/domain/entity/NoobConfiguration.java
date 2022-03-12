package com.notification.notification.domain.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoobConfiguration extends NotificationConfiguration {

    public NoobConfiguration() {
        super(7, "Você precisa usar mais nosso APP para " +
                "obter cada vez mais promoções", TypeUser.NOOB);
    }

    @Override
    public List<Hour> hoursSend() {
        var hours = new ArrayList<Hour>();
        hours.add(new Hour(12,0));
        hours.add(new Hour(18,0));

        return hours;
    }
}
