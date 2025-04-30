package com.scottlogic.grad_training.friendface.likes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("like")
public class LikeController {
  private final LikeService likeService;

  public LikeController(LikeService likeService) {
    this.likeService = likeService;
  }

  @PostMapping("/like")
  public ResponseEntity<String> likePost(String sessionToken, int postId){
    int response = likeService.like(sessionToken, postId);
    return switch (response) {
      case 428 -> new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
      case 200 -> new ResponseEntity<>(HttpStatus.OK);
      case 201 -> new ResponseEntity<>(HttpStatus.CREATED);
      default -> new ResponseEntity<>(HttpStatus.NOT_FOUND);
    };
  }
}
