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
import java.util.List;

@RestController
@RequestMapping("postManagement")
public class PostController {
  private final PostService postService;
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/getAllPosts")
  public ResponseEntity<List<PostDTO>> getAllPosts(){
    return postService.getAllPosts();
  }
  @PostMapping("/create")
  public ResponseEntity<Post> createPost(String sessionToken, String content){
      Post post = postService.createPost(sessionToken, content);
      if(post == null){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
      return new ResponseEntity<>(post,HttpStatus.CREATED);
  }
  @GetMapping("/teapot")
  public ResponseEntity<String> teapot(){
    return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
  }
  @PostMapping("/edit")
  public ResponseEntity<String> editPost(Integer postId, String content, String sessionToken){
    Post post = postService.editPost(postId, content,sessionToken);
    if(post != null){
      return new ResponseEntity<>(post.content, HttpStatus.OK);
    }else{
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
  }
  @PostMapping("/delete")
  public ResponseEntity<String> deletePost(Integer postId, String sessionToken){
    int response = postService.deletePost(postId, sessionToken);
    return switch (response) {
      case 428 -> new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
      case 404 -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
      case 401 -> new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      default -> new ResponseEntity<>(HttpStatus.OK);
    };
  }
}
