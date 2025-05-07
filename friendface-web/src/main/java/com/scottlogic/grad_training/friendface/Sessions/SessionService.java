package com.scottlogic.grad_training.friendface.Sessions;

import com.scottlogic.grad_training.friendface.user.User;
import com.scottlogic.grad_training.friendface.user.UserService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class SessionService {
  private final SessionRepository sessionRepository;
  private static final SecureRandom securerandom = new SecureRandom();
  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();


  public SessionService(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }
  public Session SessionExists(User user){
    List<Session> response = this.sessionRepository.findByUserId(user.getId());
    if(response.isEmpty()){
      return null;
    }
    return response.getFirst();
  }
  public static String generateToken(){
    byte[] randomBytes = new byte[32];
    securerandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);
  }
  public Session createSession(User user){
    Session session = new Session(user, generateToken(), LocalDateTime.now());
    sessionRepository.save(session);
    return session;
  }
  //TODO add some time based validation to check if the session token has lapsed
  public User validateSession(String sessionToken){
    Session response = sessionRepository.findBySessionToken(sessionToken)
            .orElse(null);
    if(response !=null){
      return response.getUser();
    }
    return null;
  }

  public void deleteToken(String sessionToken) {
    Session response = sessionRepository.findBySessionToken(sessionToken)
            .orElse(null);
    if(response != null){
      sessionRepository.delete(response);
    }
  }
}
