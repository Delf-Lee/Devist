package com.tdl.devist.model;

import lombok.Setter;

import java.time.LocalDate;

public class FixedRepeayDay extends RepeatDay {
    /**
     * 해당 기간동안 할일을 수행햐야하는 정해진 날들. 기본 값은 매일이다.
     */
    @Setter
    private byte dayOfWeek;

    @Override
    public boolean isTodaysTodo() {
        int today = LocalDate.now().getDayOfWeek().getValue();
        return isTodoOn(today);
    }

    @Override
    public boolean isTodoOn(int day) {
        return (dayOfWeek & (1 << (day - 1))) > 0;
    }
}
