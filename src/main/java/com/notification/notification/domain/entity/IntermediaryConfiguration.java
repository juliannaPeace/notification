package com.notification.notification.domain.entity;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class IntermediaryConfiguration extends NotificationConfiguration {

    public IntermediaryConfiguration() {
        super(7,"Você está no caminho certo, continue acessando nosso " +
                "APP para obter cada vez mais promoções", TypeUser.INTERMEDIARY);
    }

    @Override
    public List<Hour> hoursSend() {
        var hours = new ArrayList<Hour>();
        hours.add(new Hour(18,0));

        return hours;
    }

    @Override
    public List<DayOfWeek> weeksSend() {
        return Arrays.stream(DayOfWeek.values()).toList();
    }
}
