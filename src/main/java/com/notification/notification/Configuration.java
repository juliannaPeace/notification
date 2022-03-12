package com.notification.notification;

import java.util.List;

public abstract class Configuration {
    protected final int numberDaysOfWeek;
    protected final List<Hour> hours;
    protected final String message;

    public Configuration(int numberDaysOfWeek, String message) {
        this.numberDaysOfWeek = numberDaysOfWeek;
        this.hours = hoursSend();
        this.message = message;
    }

    public boolean isTimeToSend(LocalDateTimeFactory localDateTimeFactory){
        return this.hours.contains(new Hour(localDateTimeFactory.now().getHour(),
                localDateTimeFactory.now().getMinute()));
    }

    public abstract List<Hour> hoursSend();

    public int getNumberDaysOfWeek() {
        return numberDaysOfWeek;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public String getMessage() {
        return message;
    }
}
