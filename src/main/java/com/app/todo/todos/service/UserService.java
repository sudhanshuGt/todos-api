package com.app.todo.todos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.todo.todos.model.AuthRequest;
import com.app.todo.todos.model.SignupRequest;
import com.app.todo.todos.model.User;
import com.app.todo.todos.repository.UserRepo;

 
   

@Service 
public class UserService {

  
  private final UserRepo repo;
  
  private final PasswordEncoder encoder;

    public UserService(PasswordEncoder encoder, UserRepo repo) {
        this.encoder = encoder;
        this.repo = repo;
    }


  

  public User signup(SignupRequest r) {
    User u = User.builder()
        .name(r.name())
        .email(r.email())
        .password(encoder.encode(r.password()))
        .roles("USER")
        .build();
    return repo.save(u);
  }

  public User byEmail(String email) {
    return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
  }

  public User validate(AuthRequest req) {
    User u = byEmail(req.email());
    if (encoder.matches(req.password(), u.getPassword())) return u;
    throw new RuntimeException("Bad credentials");
  }

  public User byId(Long id) { return repo.findById(id).orElseThrow(); }
}


