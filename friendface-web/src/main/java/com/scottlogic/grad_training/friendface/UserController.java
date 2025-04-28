package com.scottlogic.grad_training.friendface;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserRepository userRepository;
  public UserController(UserRepository userRepository){
    this.userRepository=userRepository;
  }
  @GetMapping
  public List<User> getUsers(){
    return userRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<User> addUser(@RequestParam @NotBlank String username) {
    User user = new User(username);
    userRepository.save(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
