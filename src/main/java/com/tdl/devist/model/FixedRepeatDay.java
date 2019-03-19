package com.tdl.devist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@ToString
public class FixedRepeatDay extends RepeatDay {
    @Column(length = 1, name = "days_of_week")
    private byte byteDaysOfWeek = 127;

    @AllArgsConstructor
    public
    enum DayOfWeek {
        MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6), SUN(7);
        public final static int n = 7;
        @Getter
        private int value;
    }

    @Transient
    private DayOfWeek[] dayOfWeeks = new DayOfWeek[7];

    @Transient
    private final String[] WEEK_DAY_STR = {"월", "화", "수", "목", "금", "토", "일"};

    public void convertRepeatDayBooleanArrToByte() {
        byteDaysOfWeek = 0;
        for (DayOfWeek w : dayOfWeeks) {
            byteDaysOfWeek |= (byte) (1 << w.getValue() - 1);
        }
    }

    public void convertRepeatDayByteToBooleanArr() {
        int cur = 0;
        for (int i = 0; i < DayOfWeek.n; i++) {
            dayOfWeeks[cur] = ((byteDaysOfWeek >> i) & 1) == 1 ? DayOfWeek.values()[cur++] : null;
        }
    }

    @Override
    public boolean isOnToday() {
        int today = LocalDate.now().getDayOfWeek().getValue();
        return (byteDaysOfWeek & (1 << (today - 1))) > 0;

    }

    @Override
    public boolean isInitDay() {
        return false;
    }

    @Override
    public boolean initRepeatDay() {
        return false;
    }

    @Override
    public boolean isOn(int dayOfWeek) {
        return (byteDaysOfWeek & (1 << (dayOfWeek - 1))) > 0;
    }


}