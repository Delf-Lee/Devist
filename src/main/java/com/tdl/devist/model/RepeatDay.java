package com.tdl.devist.model;

public abstract class RepeatDay implements Repeatable {
    private Todo todo;
    /**
     * 시작 기준 날(요일). 기본 값은 월요일이다.
     */
    private byte startDay;

    protected void initStart() {
        startDay = 0;
    }
}
