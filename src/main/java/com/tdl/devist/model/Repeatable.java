package com.tdl.devist.model;

public interface Repeatable {
    /**
     * @return 오늘 할일인지에 대한 여부
     */
    public boolean isTodaysTodo();
    /**
     * @param day 특정 날짜
     * @return day에 할일인지에 대한 여부
     */
    public boolean isTodoOn(int day);
}
