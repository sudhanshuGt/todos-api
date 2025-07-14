package com.app.todo.todos.model;


/** Payload for login: email + password */
public record AuthRequest(
        String email,
        String password
) {}
