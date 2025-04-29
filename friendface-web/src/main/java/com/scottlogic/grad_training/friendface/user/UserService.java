package com.scottlogic.grad_training.friendface.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final PasswordEncoder encoder;
  private final UserRepository userRepository;
  public UserService(PasswordEncoder encoder, UserRepository userRepository){
    this.encoder = encoder;
    this.userRepository = userRepository;
  }
  public User createUser(String username, String password){
    String hashedPassword = encoder.encode(password);
    User user = new User(username, hashedPassword);
    userRepository.save(user);
    return user;
  }
}
