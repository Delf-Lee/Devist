package com.tdl.devist.model;

import lombok.Setter;

import java.time.LocalDate;

public class FixedRepeatDay extends RepeatDay {
    /**
     * 해당 기간동안 할일을 수행해 해야하는 정해진 날들. 기본 값은 매일이다.
     */
    @Setter
    private byte fixedDays;

    @Override
    public boolean isTodaysTodo() {
        int today = LocalDate.now().getDayOfWeek().getValue();
        return isTodoOn(today);
    }

    @Override
    public boolean isTodoOn(int day) {
        return (fixedDays & (1 << (day - 1))) > 0;
    }

    public boolean[] getBooleanArr() {
        boolean[] checkbox = new boolean[7];
        for (int i = 0; i < checkbox.length; i++) {
            checkbox[checkbox.length - 1 - i] = ((fixedDays >> i) & 1) == 1;
        }
        return checkbox;
    }

    public void setFixedDays(boolean[] checkbox) {
        fixedDays = 0;
        for (int i = checkbox.length - 1; i >= 0; i--) {
            fixedDays |= checkbox[i] ? (byte) (1 << (checkbox.length - 1) - i) : 0;
        }
    }
}