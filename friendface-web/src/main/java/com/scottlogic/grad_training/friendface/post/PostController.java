package com.scottlogic.grad_training.friendface.post;

import com.scottlogic.grad_training.friendface.user.User;
import com.scottlogic.grad_training.friendface.user.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("postManagement")
public class PostController {
  private final PostService postService;
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping("/createPost")
  public ResponseEntity<Post> createPost(int author_id, String content){
      Post post = postService.createPost(author_id, content);
      return new ResponseEntity<>(post,HttpStatus.OK);
  }
  @GetMapping("/teapot")
  public ResponseEntity<String> teapot(){
    return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
  }
}
