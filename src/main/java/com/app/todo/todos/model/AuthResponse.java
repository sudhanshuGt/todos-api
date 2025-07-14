package com.app.todo.todos.model;

/** Response after signup/login: just a JWT token string */
public record AuthResponse(
        String token
) {}
