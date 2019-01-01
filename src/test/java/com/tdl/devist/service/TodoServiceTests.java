package com.tdl.devist.service;


import com.tdl.devist.model.Todo;
import com.tdl.devist.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("dev")
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    private final String TEST_USER_NAME = "admin";
    private final String TEST_TODO_TITLE = "Todo 테스트하기";

    @Test
    public void 서비스_레이어에서_Todo_추가_테스트() {
        User user = userService.getUserByUserName(TEST_USER_NAME);

        Todo todo = generateTestTodoInstance();
        todo.setUser(user);
        user.addTodo(todo);
        userService.updateUser(user);

        User targetUser = userService.getUserByUserName(TEST_USER_NAME);
        List<Todo> todoList = targetUser.getTodoList();
        Assert.assertEquals(1, todoList.size());
        Assert.assertEquals(TEST_TODO_TITLE, todoList.get(0).getTitle());
    }

    private Todo generateTestTodoInstance() {
        Todo todo = new Todo();
        todo.setTitle(TEST_TODO_TITLE);
        return todo;
    }
}