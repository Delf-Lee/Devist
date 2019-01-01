package com.tdl.devist.service;

import com.tdl.devist.model.Todo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @author delf
 */
@Service
public class TodoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addTodo(Todo todo) {
        todo.setCreatedTime(LocalDateTime.now());
        entityManager.persist(todo);
    }
}
