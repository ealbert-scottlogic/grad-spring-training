package com.scottlogic.grad_training.friendface;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @GetMapping
  @RequestMapping("/greetings")
  public String index() {
    return "la;sdfijbhg;bjkhadfs <h1> heading </h1>";
  }
}