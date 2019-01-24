package com.tdl.devist.model;

import lombok.Setter;

import java.time.LocalDate;

@Setter
public class FlexibleRepeatDay extends RepeatDay {
    /**
     * 기간 내 해당 할일을 수행해야 하는 횟수
     */
    private int weeksCount;
    /**
     * 기간 내 해당 할일을 수행 완료한 횟수
     */
    private int doingCount;

    /**
     * @return 기간내 수행해야 할 횟수를 모두 만족했는지 여부
     */
    public boolean isComplete() {
        return !(doingCount > weeksCount);
    }

    @Override
    protected void initStart() {
        super.initStart();
        weeksCount = 0;
        doingCount = 0;
    }

    @Override
    public boolean isTodaysTodo() {
        return !isComplete();
    }

    @Override
    public boolean isTodoOn(int day) {
        int today = LocalDate.now().getDayOfWeek().getValue();
        return (today <= day) && isTodaysTodo();
    }

    /**
     * @return 일주일 기준으로 오늘 이후에 요일에는 true, 나머지 false인 boolean[]
     */
//    public boolean[] getBooleanArr() {
//        boolean[] res = new boolean[7];
//        int today = LocalDate.now().getDayOfWeek().getValue();
//        for (int i = today - 1; i < res.length; i++) {
//            res[i] = true;
//        }
//        return res;
//    }
}
