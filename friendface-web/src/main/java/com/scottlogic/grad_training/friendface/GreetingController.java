package com.scottlogic.grad_training.friendface;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class GreetingController {

  private final PostsService postsService;
  public GreetingController(PostsService postsService){
    this.postsService= postsService;
  }
  @GetMapping
  @RequestMapping("Greeting/{locale}")
  public String localized(@PathVariable String locale){
    switch(locale){
      case "fr":
        return "Bonjour";
      case "nl":
        return "Hoi!";
    }
    return "Hello!";
  }

  @GetMapping("/post")
  public Post postOfTheMoment(){
    final Post post = new Post();
    post.setAuthor("Name mc nameson");
    post.setContent("Some content that someone might be writing about");
    return post;
  }

  @PostMapping("/welcome")
  public String welcome(@RequestBody Post post){
    return String.format("Thank you for your post %s", post.getAuthor());
  }
  @GetMapping("/random")
  public Post randomPost(){
    return postsService.randomPost();
  }
}

