package com.tdl.devist.dto;

import com.tdl.devist.model.FixedRepeatDay;
import com.tdl.devist.model.FlexibleRepeatDay;
import com.tdl.devist.model.RepeatDay;
import com.tdl.devist.model.Todo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author delf
 */
@Getter
@Setter
public class TodoDto {
    private String title;
    private String description;

    private String type;

    private FixedRepeatDay.DayOfWeek[] dayOfWeeks = FixedRepeatDay.DayOfWeek.values();
    private int doingCount;

    public Todo generateNewTodo() throws Exception {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        RepeatDay repeatDay = getRepeatDay();
        repeatDay.setTodo(todo);
        todo.setRepeatDay(repeatDay);
        return todo;
    }

    public Todo getUpdatedTodo(Todo todo) throws Exception {
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setRepeatDay(getRepeatDay());
        return todo;
    }

    public RepeatDay getRepeatDay() throws Exception {
        System.out.println("@@ type: " + type);
        switch (Todo.Type.valueOf(type)) {
            case FIXED:
                FixedRepeatDay fixedRepeatDay = new FixedRepeatDay();
                fixedRepeatDay.setDayOfWeeks(dayOfWeeks);
                fixedRepeatDay.convertRepeatDayBooleanArrToByte();
                return fixedRepeatDay;
            case FLEXIBLE:
                FlexibleRepeatDay flexibleRepeatDay = new FlexibleRepeatDay();
                flexibleRepeatDay.setDoingCount(doingCount);
                return flexibleRepeatDay;
        }
        throw new Exception(); // TODO: 예외 관리하기
    }
}