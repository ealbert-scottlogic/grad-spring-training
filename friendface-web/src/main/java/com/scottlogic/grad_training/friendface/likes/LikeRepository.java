package com.scottlogic.grad_training.friendface.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like, Integer> {
  public List<Like> findByPostIdAndUserId(int postId, int userId);
  public List<Like> findByPostId(int postId);

  @Query(value = "SELECT user_id FROM likes  WHERE post_id = :postId",
          nativeQuery = true)
  public List<Integer> findUserIdByPostId(@Param("postId") Integer postId);

  @Query(value = "select count(l.id) from likes l inner join posts p on l.post_id = p.id where p.author_id = :user_id",
          nativeQuery = true)
  public Integer findTotalUserLikes(@Param("user_id") Integer userId);
}
