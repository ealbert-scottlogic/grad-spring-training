package com.scottlogic.grad_training.friendface.likes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like, Integer> {
  public List<Like> findByPostIdAndUserId(int postId, int userId);
}
