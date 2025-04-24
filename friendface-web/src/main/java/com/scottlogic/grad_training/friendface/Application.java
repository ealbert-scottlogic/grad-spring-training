package com.scottlogic.grad_training.friendface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

@SpringBootApplication()
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}