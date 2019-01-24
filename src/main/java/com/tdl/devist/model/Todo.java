package com.tdl.devist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "todos")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private int id;
    private String title;
    private String description;
    /*@Column(length = 1)
    private byte repeatDay = 127;*/
    private RepeatDay repeatDay;
    private LocalDateTime createdTime;
    private double doneRate = 0.0;
    @OneToOne
    @JoinColumn(name = "latest_daily_check_id")
    private DailyCheck latestDailyCheck;

    @Transient
    private boolean[] repeatCheckbox = {true, true, true, true, true, true, true};
    @Transient
    private final String[] WEEK_DAY = {"일", "월", "화", "수", "목", "금", "토"};

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private List<DailyCheck> dailyChecks = new ArrayList<>();

    public boolean addDailyCheck(DailyCheck dailyCheck) {
        return dailyChecks.add(dailyCheck);
    }

    public boolean isDone() {
        if (latestDailyCheck == null) {
            // Todo: latestDaliyCheck 가 없다는 로그 출력
            return false;
        }
        return latestDailyCheck.isDone();
    }

    public boolean isTodaysTodo() {
        return repeatDay.isTodaysTodo();
    }

    // NOTE:
    public void setRepeayDay(boolean[] checkbox) {
        ((FixedRepeatDay) repeatDay).setFixedDays(checkbox);
    }

    public void convertRepeatDayByteToBooleanArr(){

    }

    public void convertRepeatDayBooleanArrToByte() {

    }
}
