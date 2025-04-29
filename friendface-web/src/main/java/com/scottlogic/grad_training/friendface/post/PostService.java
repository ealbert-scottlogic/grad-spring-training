package com.scottlogic.grad_training.friendface.post;

import com.scottlogic.grad_training.friendface.user.User;
import com.scottlogic.grad_training.friendface.user.UserService;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class PostService {
  private final UserService userService;
  private final PostRepository postRepository;
  public PostService(UserService userService, PostRepository postRepository) {
    this.userService = userService;
    this.postRepository = postRepository;
  }

  public Post createPost(Integer authorId, String content){
    User user = userService.getUserById(authorId);
    Post post = new Post(content, user);
    LocalDateTime date = LocalDateTime.now();
    post.setDate(date);
    postRepository.save(post);

    return post;
  }
}
