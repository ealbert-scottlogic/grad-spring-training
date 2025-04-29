package com.scottlogic.grad_training.friendface.Sessions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scottlogic.grad_training.friendface.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name="sessions")
public class Session {

  @OneToOne
  @JoinColumn(name="userId", nullable = false)
  User user;
  @Id
  @Column
  private String sessionToken;
  @Column
  private LocalDateTime lastLogin;

  public Session(){}
  public Session(User user, String sessionToken, LocalDateTime lastLogin) {
    this.user = user;
    this.sessionToken = sessionToken;
    this.lastLogin = lastLogin;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getSessionToken() {
    return sessionToken;
  }

  public void setSessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
  }

  public LocalDateTime getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(LocalDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }
}
