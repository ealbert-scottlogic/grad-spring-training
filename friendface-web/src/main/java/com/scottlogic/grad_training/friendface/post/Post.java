package com.scottlogic.grad_training.friendface.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scottlogic.grad_training.friendface.user.User;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="posts")
public class Post {
  @Column
  @Id
  int id;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne
  @JoinColumn(name="author_id", nullable = false)
  User author;
  @Column
  String content;
  @Column
  LocalDateTime date;
  public Post(){}
  public Post(String content, User author){
    this.content = content;
    this.author = author;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}
