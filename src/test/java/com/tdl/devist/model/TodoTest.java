package com.tdl.devist.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Profile;

import com.tdl.devist.model.FixedRepeatDay.DayOfWeek;

import java.time.LocalDate;

@Profile("dev")
@RunWith(MockitoJUnitRunner.class)
public class TodoTest {

    private final String TEST_USER_NAME = "my_name_is_user";
    private final String TEST_TODO_TITLE = "Todo 테스트하기 in TodoTest.class";


    @Test
    public void 변환테스트_byte에서_booleanArr() {
        // TODO: 테스트 코드 작성
    }

    @Test
    public void 변환테스트_booleanArr에서_byte() {
        // TODO: 테스트 코드 작성
    }

    @Test
    public void 요일_고정_할일이_오늘_할일이다() {
        // given
        FixedRepeatDay repeatDay = new FixedRepeatDay();

        // when
        repeatDay.setDayOfWeeks(new DayOfWeek[]{DayOfWeek.MON, DayOfWeek.THU});
        repeatDay.convertRepeatDayBooleanArrToByte();
        System.out.println(repeatDay.getByteDaysOfWeek());

        // then
        Assert.assertTrue(repeatDay.isOn(DayOfWeek.MON.getValue()));
        Assert.assertTrue(repeatDay.isOn(DayOfWeek.THU.getValue()));

    }

    @Test
    public void 요일_고정_할일이_오늘_할일이_아니다() {

        // given
        FixedRepeatDay repeatDay = new FixedRepeatDay();

        // when
        repeatDay.setDayOfWeeks(new DayOfWeek[]{DayOfWeek.MON, DayOfWeek.THU});
        repeatDay.convertRepeatDayBooleanArrToByte();
        System.out.println(repeatDay.getByteDaysOfWeek());

        // then
        Assert.assertFalse(repeatDay.isOn(DayOfWeek.TUE.getValue()));
        Assert.assertFalse(repeatDay.isOn(DayOfWeek.FRI.getValue()));
        Assert.assertFalse(repeatDay.isOn(DayOfWeek.SAT.getValue()));
        Assert.assertFalse(repeatDay.isOn(DayOfWeek.SUN.getValue()));
        Assert.assertFalse(repeatDay.isOn(DayOfWeek.WED.getValue()));
    }

    @Test
    public void 요일_유동_할일이_오늘_할일이다() {
        /*// given
        FlexibleRepeatDay repeatDay = new FlexibleRepeatDay();
        Todo todo = new Todo();
        repeatDay.setTodo(todo);

        // when
        repeatDay.setDoingCount(3);

        todo.addDailyCheck(generateTestDailyCheck(LocalDate.of(2018, 12, 25), true));
        todo.addDailyCheck(generateTestDailyCheck(LocalDate.of(2018, 12, 25), true));
        todo.addDailyCheck(generateTestDailyCheck(LocalDate.of(2018, 12, 25), false)); // 오늘

        // then
        Assert.assertTrue(repeatDay.isOn(DayOfWeek.FRIDAY.getValue()));*/
    }

    @Test
    public void 요일_유동_할일이_완료된_할일이다() {
        /*// given
        FlexibleRepeatDay repeatDay = new FlexibleRepeatDay();
        Todo todo = new Todo();
        repeatDay.setTodo(todo);

        // when
        repeatDay.setDoingCount(3);

        todo.addDailyCheck(generateTestDailyCheck(LocalDate.of(2018, 12, 25), true));
        todo.addDailyCheck(generateTestDailyCheck(LocalDate.of(2018, 12, 25), true));
        todo.addDailyCheck(generateTestDailyCheck(LocalDate.of(2018, 12, 25), true));
        todo.addDailyCheck(generateTestDailyCheck(LocalDate.of(2018, 12, 25), false)); // 오늘

        // then
        Assert.assertFalse(repeatDay.isOn(DayOfWeek.FRIDAY.getValue()));*/
    }

    private DailyCheck generateTestDailyCheck(LocalDate localDate, boolean isDone) {
        DailyCheck dailyCheck = new DailyCheck();
        dailyCheck.setPlanedDate(localDate);
        dailyCheck.setDone(isDone);
        return dailyCheck;
    }

    private User generateTestUserInstance() {
        User user = new User();
        user.setUsername(TEST_USER_NAME);
        user.setPassword("1234");
        return user;
    }

    private Todo generateTestTodoInstance() {
        Todo todo = new Todo();
        todo.setTitle(TEST_TODO_TITLE);
        return todo;
    }
}