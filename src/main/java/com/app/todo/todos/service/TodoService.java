package com.app.todo.todos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.app.todo.todos.model.Todo;
import com.app.todo.todos.model.User;
import com.app.todo.todos.repository.TodoRepo;

@Service 
public class TodoService {


  private final TodoRepo repo;

  private final UserService users;

    public TodoService(TodoRepo repo, UserService users) {
        this.repo = repo;
        this.users = users;
    }



  private User current() {
    Long id = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return users.byId(id);
  }

  public List<Todo> allMine() { return repo.findByUser(current()); }

  public Todo add(Todo t) { t.setUser(current()); return repo.save(t); }

  public Todo update(Long id, Todo p) {
    Todo t = repo.findById(id).orElseThrow();
    if (p.getTodo() != null) t.setTodo(p.getTodo());
    t.setCompleted(p.isCompleted());
    return repo.save(t);
  }

  public void delete(Long id) { repo.deleteById(id); }
}
