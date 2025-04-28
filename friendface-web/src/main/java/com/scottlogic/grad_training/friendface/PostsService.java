package com.scottlogic.grad_training.friendface;

import org.springframework.stereotype.Service;

@Service
public class PostsService {
  public Post randomPost(){
    Post post = new Post();
    post.setAuthor(""+ Math.random());
    post.setContent("" + Math.random());
    return post;
  }
}
