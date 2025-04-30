package com.scottlogic.grad_training.friendface.post;

import com.scottlogic.grad_training.friendface.Sessions.Session;
import com.scottlogic.grad_training.friendface.Sessions.SessionService;
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
  private final SessionService sessionService;
  private final UserService userService;
  private final PostRepository postRepository;
  public PostService(SessionService sessionService, UserService userService, PostRepository postRepository) {
    this.sessionService = sessionService;
    this.userService = userService;
    this.postRepository = postRepository;
  }
  //TODO implement this
  public Boolean validateContent(String content){
    return true;
  }
  public Post editPost(Integer postId, String content, String sessionToken){
    if(!validateContent(content)){
      return null;
    }
    if(postRepository.existsById(postId)){
      Post post = postRepository.getReferenceById(postId);
      User user = sessionService.validateSession(sessionToken);
      if(user != null){
        if (post.getAuthor().equals(user)){
          post.setDate(LocalDateTime.now());
          post.setContent(content);
          postRepository.save(post);
          return post;
        }
      }
    }
    return null;
  }
  public Post createPost(String sessionToken, String content){
    User user = sessionService.validateSession(sessionToken);
    if(user == null){
      return null;
    }
    Post post = new Post(content, user);
    LocalDateTime date = LocalDateTime.now();
    post.setDate(date);
    postRepository.save(post);
    return post;
  }
}
