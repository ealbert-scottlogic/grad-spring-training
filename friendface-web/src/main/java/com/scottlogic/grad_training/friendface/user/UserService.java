package com.scottlogic.grad_training.friendface.user;

import com.scottlogic.grad_training.friendface.Sessions.Session;
import com.scottlogic.grad_training.friendface.Sessions.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<User> createUser(String username, String password){
    if(!userRepository.findByUsername(username).isEmpty()){
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    String hashedPassword = encoder.encode(password);
    User user = new User(username, hashedPassword);
    userRepository.save(user);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  public User getUserById(int userID){
    return userRepository.getReferenceById(userID);
  }

  public ResponseEntity<String> attemptLogin(String username, String password) {
    User response = userRepository.findByUsername(username).orElse(null);
    if(response == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if(encoder.matches(password, response.getPassword())){
      //Do a lookup to see if a token already exists
      Session sessionResponse = sessionService.SessionExists(response);
      if(sessionResponse == null){
        return new ResponseEntity<>(sessionService.createSession(response).getSessionToken(), HttpStatus.CREATED);
      }else{
        return new ResponseEntity<>(sessionResponse.getSessionToken(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }

  public ResponseEntity<String> changeUsername(String username, String sessionToken) {
    User user = sessionService.validateSession(sessionToken);
    if(user == null){
      // Session token does not exist
      return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
    }
    if(userRepository.findByUsername(username).isPresent()){
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    user.setUsername(username);
    userRepository.save(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<String> deleteUser(String sessionToken) {
    User user = sessionService.validateSession(sessionToken);
    if(user == null){
      return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
    }
    userRepository.delete(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  public ResponseEntity<Boolean> logout(String sessionToken) {
    User user = sessionService.validateSession(sessionToken);
    if(user == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    sessionService.deleteToken(sessionToken);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
