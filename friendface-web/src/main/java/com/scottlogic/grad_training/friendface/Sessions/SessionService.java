package com.scottlogic.grad_training.friendface.Sessions;

import com.scottlogic.grad_training.friendface.user.User;
import com.scottlogic.grad_training.friendface.user.UserService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class SessionService {
  private final SessionRepository sessionRepository;
  private static final SecureRandom securerandom = new SecureRandom();
  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();

  public SessionService(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
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
}
