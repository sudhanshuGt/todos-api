package com.app.todo.todos.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.app.todo.todos.model.AuthRequest;
import com.app.todo.todos.model.AuthResponse;
import com.app.todo.todos.model.SignupRequest;
import com.app.todo.todos.util.JwtUtil;
 
import com.app.todo.todos.service.*;
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {


  private final UserService users;

  private final JwtUtil jwt;

    public AuthController(JwtUtil jwt, UserService users) {
        this.jwt = jwt;
        this.users = users;
    }



  @PostMapping("/signup")
  public AuthResponse signup(@RequestBody SignupRequest r) {
    var u = users.signup(r);
    return new AuthResponse(jwt.generate(u.getId(), u.getRoles()));
  }

  @PostMapping("/login")
  public AuthResponse login(@RequestBody AuthRequest r) {
    var u = users.validate(r);
    return new AuthResponse(jwt.generate(u.getId(), u.getRoles()));
  }
}
