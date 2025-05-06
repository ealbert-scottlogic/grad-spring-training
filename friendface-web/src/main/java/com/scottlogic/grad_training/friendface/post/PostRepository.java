package com.scottlogic.grad_training.friendface.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
  @Query(value = "select author_id, content, date, id, likes, username from" +
          "(select * from posts p left join " +
          "(select post_id , count(id)as likes from likes l group by post_id) l " +
          "on p.id = l.post_id)q " +
          "left join " +
          "(select id as user_id ,username  from users u ) u on q.author_id = u.user_id",
          nativeQuery = true)
  List<PostDTO> getAllPosts();
}
