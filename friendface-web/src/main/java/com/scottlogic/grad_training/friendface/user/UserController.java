package com.scottlogic.grad_training.friendface.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;

@RestController
@RequestMapping("userManagement")
public class UserController {

  private final UserService userService;
  public UserController(UserService userService){
    this.userService = userService;
  }
  @PostMapping
  public ResponseEntity<User> createUser(String username, String password){
    User user = userService.createUser(username,password);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

}
