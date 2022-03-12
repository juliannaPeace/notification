package com.notification.notification.domain.entity;

import java.util.Objects;

public class Hour {
    public int hour;
    public int minute;

    public Hour(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hour hour1 = (Hour) o;
        return hour == hour1.hour && minute == hour1.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }
}
