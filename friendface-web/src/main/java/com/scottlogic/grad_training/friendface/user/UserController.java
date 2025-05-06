package com.scottlogic.grad_training.friendface.user;

import com.scottlogic.grad_training.friendface.Sessions.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.PasswordAuthentication;

@RestController
@RequestMapping("userManagement")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/createUser")
  public ResponseEntity<User> createUser(@RequestBody LoginRequestDTO loginDetails) {
    return userService.createUser(loginDetails.getUsername(),loginDetails.getPassword());
  }
  @PostMapping("/deleteAccount")
  public ResponseEntity<String> deleteUser(String sessionToken){
    return userService.deleteUser(sessionToken);
  }
  @PostMapping("/changeUsername")
  public ResponseEntity<String> changeUsername(String username, String sessionToken){
    return userService.changeUsername(username, sessionToken);
  }

  @PostMapping("/login")
  public ResponseEntity<String> attemptLogin(@RequestBody LoginRequestDTO loginDetails) {
    return userService.attemptLogin(loginDetails.getUsername(),loginDetails.getPassword());
  }
}