package com.scottlogic.grad_training.friendface.likes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("like")
public class LikeController {
  private final LikeService likeService;

  public LikeController(LikeService likeService) {
    this.likeService = likeService;
  }

  @PostMapping("/like")
  public ResponseEntity<String> likePost(String sessionToken, int postId){
    return likeService.like(sessionToken,postId);
  }

  @PostMapping("/getLikes")
  public ResponseEntity<Integer> getLikes(int postId){
    return likeService.getLikes(postId);
  }

  @PostMapping("/getUserLikes")
  public ResponseEntity<List<Integer>> getUserLikes(int postId){
    return likeService.getUserLikes(postId);
  }
}
