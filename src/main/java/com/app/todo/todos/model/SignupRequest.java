package com.app.todo.todos.model;

public record SignupRequest(
        String name,
        String email,
        String password
) {}