package com.notification.notification;

import java.util.ArrayList;
import java.util.List;

public class IntermediaryConfiguration extends Configuration{

    public IntermediaryConfiguration() {
        super(7,"Você está no caminho certo, continue acessando nosso " +
                "APP para obter cada vez mais promoções");
    }

    @Override
    public List<Hour> hoursSend() {
        var hours = new ArrayList<Hour>();
        hours.add(new Hour(18,0));

        return hours;
    }
}
