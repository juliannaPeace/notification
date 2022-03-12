package com.notification.notification.domain.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdvancedConfiguration extends NotificationConfiguration {

    public AdvancedConfiguration() {
        super(2, "Você é um dos nosso melhores usuários, continue acessando nosso APP " +
                "para obter cada vez mais promoções", TypeUser.ADVANCED);
    }

    @Override
    public List<Hour> hoursSend() {
        var hours = new ArrayList<Hour>();
        hours.add(new Hour(22,0));

        return hours;
    }
}
