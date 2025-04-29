package com.scottlogic.grad_training.friendface.user;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="users")
public class User {
  @Id
  @Column
  private int id;
  @Column
  private String username;
  @Column
  private String password;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
  public User(){}
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
