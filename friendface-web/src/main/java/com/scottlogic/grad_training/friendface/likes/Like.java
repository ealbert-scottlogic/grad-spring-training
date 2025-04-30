package com.scottlogic.grad_training.friendface.likes;

import com.scottlogic.grad_training.friendface.post.Post;
import com.scottlogic.grad_training.friendface.user.User;
import jakarta.persistence.*;

@Entity
@Table(name="likes")
public class Like {
  @Column
  @Id
  int id;

  @ManyToOne
  @JoinColumn(name="post_id", nullable = false)
  Post post;
  @ManyToOne
  @JoinColumn(name="user_id", nullable = false)
  User user;
  public Like(){}
  public Like(Post post, User user) {
    this.post = post;
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
