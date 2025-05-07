package com.scottlogic.grad_training.friendface.post;

public class PostReciverDTO {
  private String sessionToken;
  private String content;

  public String getSessionToken() {
    return sessionToken;
  }

  public void setSessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
