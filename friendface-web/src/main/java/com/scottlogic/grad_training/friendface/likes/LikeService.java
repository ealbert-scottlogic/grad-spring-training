package com.scottlogic.grad_training.friendface.likes;

import com.scottlogic.grad_training.friendface.Sessions.SessionService;
import com.scottlogic.grad_training.friendface.post.Post;
import com.scottlogic.grad_training.friendface.post.PostRepository;
import com.scottlogic.grad_training.friendface.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
  private final SessionService sessionService;
  private final PostRepository postRepository;
  private final LikeRepository likeRepository;
  public LikeService(SessionService sessionService,
                     PostRepository postRepository,
                     LikeRepository likeRepository) {
    this.sessionService = sessionService;
    this.postRepository = postRepository;
    this.likeRepository = likeRepository;
  }

  public int like(String sessionToken, int postId) {
    User user = sessionService.validateSession(sessionToken);
    if(user == null){
      // No session token for user
      return 428;
    }
    if(postRepository.existsById(postId)){
      Post post = postRepository.getReferenceById(postId);
      List<Like> likes = likeRepository.findByPostIdAndUserId(postId, user.getId());
      if(!likes.isEmpty()){
        // Send ok if like entry deleted
        likeRepository.delete(likes.getFirst());
        return 200;
      }else{
        // Send created if like entry created
        likeRepository.save(new Like(post,user));
        return 201;
      }
    }
    // post not found
    return 404;
  }
}
