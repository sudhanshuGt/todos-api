package com.app.todo.todos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.todo.todos.model.User;


public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

