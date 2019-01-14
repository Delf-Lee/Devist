package com.tdl.devist.controller;

import com.sun.tracing.dtrace.ProviderAttributes;
import com.tdl.devist.model.Todo;
import com.tdl.devist.model.User;
import com.tdl.devist.service.TodoService;
import com.tdl.devist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final UserService userService;
    private final TodoService todoService;

    @Autowired
    public TodoController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    @GetMapping
    public String getTodoList(Principal principal, Model model) {
        User user = userService.getUserByUserName(principal.getName());
        List<Todo> todoList = user.getTodoList();

        model.addAttribute("todo_list", todoList);

        return "todo_list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("todo", new Todo());

        return "addtodo";
    }

    @PostMapping("/add")
    public String add(final Principal principal, Todo todo) {
        User user = userService.getUserByUserName(principal.getName());
        todo.convertRepeatDayBooleanArrToByte(); // todo: 이슈 #17 참고
        todoService.addTodo(user, todo);

        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id, final Principal principal) {
        User user = userService.getUserByUserName(principal.getName());
        todoService.deleteTodo(user, id);

        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable int id, final Principal principal) {
        Todo todo = todoService.findTodoById(id);
        if(userService.hasAuthorization(principal.getName(), todo )) {
            return "redirect:/denied";
        }
        todo.convertRepeatDayByteToBooleanArr();
        model.addAttribute("todo", todo);

        return "edittodo";
    }

    @PostMapping("/{id}/edit")
    public String edit(Todo todo, @PathVariable int id) {
        todo.convertRepeatDayBooleanArrToByte(); // todo: 이슈 #17 참고
        todoService.updateTodo(id, todo);

        return "redirect:/";
    }

    @PostMapping("/{id}/do")
    public @ResponseBody String doTodo(@PathVariable int id, @RequestParam boolean isDone, final Principal principal) {
        todoService.setTodoIsDone(id, isDone);
        todoService.updateDoneRate(id);
        userService.updateDoneRate(principal.getName());
        return "ok";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        Todo todo = todoService.findTodoById(id);
        model.addAttribute("todo", todo);
        return "todo_detail";

    }
}