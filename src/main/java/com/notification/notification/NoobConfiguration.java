package com.notification.notification;

import java.util.ArrayList;
import java.util.List;

public class NoobConfiguration extends Configuration {

    public NoobConfiguration() {
        super(7, "Você precisa usar mais nosso APP para " +
                "obter cada vez mais promoções");
    }

    @Override
    public List<Hour> hoursSend() {
        var hours = new ArrayList<Hour>();
        hours.add(new Hour(12,0));
        hours.add(new Hour(18,0));

        return hours;
    }
}
