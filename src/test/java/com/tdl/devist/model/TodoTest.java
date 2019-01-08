package com.tdl.devist.model;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.Profile;

import javax.transaction.Transactional;
import java.util.Arrays;


@Profile("dev")
public class TodoTest {

    private final String TEST_USER_NAME = "my_name_is_user";
    private final String TEST_TODO_TITLE = "Todo 테스트하기 in TodoTest.class";

    @Test
    @Transactional
    public void 변환테스트() {
        Todo todo = generateTestTodoInstance();
        todo.setRepeatDay((byte) 1);
        todo.convertRepeatDayByteToBooleanArr();
        Assert.assertEquals(Arrays.toString(new boolean[]{false, false, false, false, false, false, true}), Arrays.toString(todo.getRepeatCheckbox()));

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