package com.scottlogic.grad_training.friendface.user;

import com.scottlogic.grad_training.friendface.Sessions.Session;
import com.scottlogic.grad_training.friendface.Sessions.SessionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
  private final SessionService sessionService;
  private final PasswordEncoder encoder;
  private final UserRepository userRepository;
  public UserService(SessionService sessionService,
                     PasswordEncoder encoder,
                     UserRepository userRepository){
    this.sessionService = sessionService;
    this.encoder = encoder;
    this.userRepository = userRepository;
  }
  public User createUser(String username, String password){
    if(!userRepository.findByUsername(username).isEmpty()){
      return null;
    }
    String hashedPassword = encoder.encode(password);
    User user = new User(username, hashedPassword);
    userRepository.save(user);
    return user;
  }

  public User getUserById(int userID){
    return userRepository.getReferenceById(userID);
  }

  public Session attemptLogin(String username, String password) {
    List<User> response = userRepository.findByUsername(username);
    if(response.isEmpty()){
      return null;
    }
    User user = response.getFirst();
    if(encoder.matches(password, user.getPassword())){
      return sessionService.createSession(user);
    }
    return null;
  }

  public int changeUsername(String username, String sessionToken) {
    User user = sessionService.validateSession(sessionToken);
    if(user == null){
      // Session token does not exist
      return 428;
    }
    if(!userRepository.findByUsername(username).isEmpty()){
      return 409;
    }
    user.setUsername(username);
    userRepository.save(user);
    return 200;
  }

  public int deleteUser(String sessionToken) {
    User user = sessionService.validateSession(sessionToken);
    if(user == null){
      return 428;
    }
    userRepository.delete(user);
    return 200;
  }
}
