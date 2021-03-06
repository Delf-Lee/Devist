package com.tdl.devist.model;

import com.tdl.devist.repository.TodoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("dev")
public class DailyCheckTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    @Transactional
    public void testCreateDailyCheck() {
        User user = new User();
        user.setUsername("delf");
        user.setPassword("1234");

        Todo todo = new Todo();
        todo.setUser(user);

        DailyCheck dc1 = new DailyCheck();
        dc1.setTodo(todo);
        LocalDate d1 = LocalDate.now();
        dc1.setPlanedDate(d1);
        dc1.setDone(true);

        todo.addDailyCheck(dc1);

        DailyCheck dc2 = new DailyCheck();
        dc2.setTodo(todo);
        LocalDate d2 = LocalDate.now();
        dc2.setPlanedDate(d2);
        dc2.setDone(false);

        todo.addDailyCheck(dc2);

        todoRepository.save(todo);

        List<DailyCheck> dailyChecks = todoRepository.getOne(todo.getId()).getDailyChecks();
        Assert.assertEquals(2, dailyChecks.size());

        Assert.assertEquals(dc1.getId(), dailyChecks.get(0).getId());
        Assert.assertEquals(dc1.isDone(), dailyChecks.get(0).isDone());
        Assert.assertEquals(dc1.getPlanedDate(), dailyChecks.get(0).getPlanedDate());

        Assert.assertEquals(dc2.getId(), dailyChecks.get(1).getId());
        Assert.assertEquals(dc2.isDone(), dailyChecks.get(1).isDone());
        Assert.assertEquals(dc2.getPlanedDate(), dailyChecks.get(1).getPlanedDate());

        Todo resTodo = todoRepository.getOne(todo.getId());
        List<DailyCheck> set = resTodo.getDailyChecks();
        Assert.assertTrue(set.contains(dc1));
        Assert.assertTrue(set.contains(dc2));
    }
}
