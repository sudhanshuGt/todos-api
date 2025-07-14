package com.app.todo.todos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.todo.todos.model.Todo;
import com.app.todo.todos.model.User;
 

public interface TodoRepo extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}

