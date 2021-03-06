package com.notification.notification.domain.entity;

import com.notification.notification.LocalDateTimeFactory;

import java.time.DayOfWeek;
import java.util.List;

public abstract class NotificationConfiguration {
    protected final int numberDaysOfWeek;
    protected final List<Hour> hours;
    protected final String message;
    protected final TypeUser typeUser;
    protected final List<DayOfWeek> weeksDay;

    public NotificationConfiguration(int numberDaysOfWeek, String message, TypeUser typeUser) {
        this.numberDaysOfWeek = numberDaysOfWeek;
        this.hours = hoursSend();
        this.message = message;
        this.typeUser = typeUser;
        this.weeksDay = weeksSend();
    }

    public boolean isTimeToSend(LocalDateTimeFactory localDateTimeFactory){
        return this.hours.contains(new Hour(localDateTimeFactory.now().getHour(),
                localDateTimeFactory.now().getMinute()))
                &&
                this.weeksDay.contains(localDateTimeFactory.dayOfWeek());
    }

    public abstract List<Hour> hoursSend();
    public abstract List<DayOfWeek> weeksSend();

    public int getNumberDaysOfWeek() {
        return numberDaysOfWeek;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public String getMessage() {
        return message;
    }

    public boolean isTypeUser(TypeUser typeUser) {
        return this.typeUser == typeUser;
    }
}
