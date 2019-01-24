package com.tdl.devist.model;
/**
 * 반복되기 떄문에 필요한 로직들
 */
public interface Repeatable {
    /**
     * @return 오늘 할일인지에 대한 여부
     */
    boolean isTodaysTodo();
    /**
     * @param day 특정 날짜
     * @return day에 할일인지에 대한 여부
     */
    boolean isTodoOn(int day);
}
