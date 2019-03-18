package com.tdl.devist.dto;

import com.tdl.devist.model.FixedRepeatDay;
import com.tdl.devist.model.FlexibleRepeatDay;
import com.tdl.devist.model.RepeatDay;
import com.tdl.devist.model.Todo;
import lombok.Getter;

/**
 * @author delf
 */
public class TodoDto {
    @Getter
    private String title;
    @Getter
    private String description;

    @Getter
    private Todo.Type type;
    private FixedRepeatDay fixedRepeatDay;
    private FlexibleRepeatDay flexibleRepeatDay;

    public RepeatDay getRepeatDay() throws Exception {
        switch (type) {
            case FIXED:
                return fixedRepeatDay;
            case FLEXIBLE:
                return flexibleRepeatDay;
        }
        throw new Exception(); // TODO: 예외 관리하기
    }

    public Todo generateNewTodo() throws Exception {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setRepeatDay(getRepeatDay());
        return todo;
    }

}