package com.scottlogic.grad_training.friendface.post;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PostDTO {
  private Integer author_id;
  private String content;
  private Timestamp date;
  private Integer id;
  private Long likes;
  private String username;

  public PostDTO(Integer author_id, String content, Timestamp date, Integer id, Long likes, String username) {
    this.author_id = author_id;
    this.content = content;
    this.date = date;
    this.id = id;
    this.likes = likes;
    this.username = username;
  }

  public int getAuthor_id() {
    return author_id;
  }

  public void setAuthor_id(Integer author_id) {
    this.author_id = author_id;
  }

  public PostDTO(){}

  public Long getLikes() {
    return likes;
  }

  public void setLikes(Long likes) {
    this.likes = likes;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getDate() {
    return date.toLocalDateTime();
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }
}
