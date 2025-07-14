package com.app.todo.todos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo.todos.model.Todo;
import com.app.todo.todos.service.TodoService;

import lombok.RequiredArgsConstructor;


@RestController @RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin
public class TodoController {
  private final TodoService svc;

  @GetMapping()
  public List<Todo> mine() { return svc.allMine(); }

  @PostMapping()
  public Todo add(@RequestBody Todo t) { return svc.add(t); }

  @PutMapping("/{id}") @PatchMapping("/{id}")
  public Todo upd(@PathVariable long id, @RequestBody Todo p) { return svc.update(id, p); }

  @DeleteMapping("/{id}")
  public void del(@PathVariable long id) { svc.delete(id); }
}
