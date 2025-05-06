package com.scottlogic.grad_training.friendface.likes;

import com.scottlogic.grad_training.friendface.Sessions.SessionService;
import com.scottlogic.grad_training.friendface.post.Post;
import com.scottlogic.grad_training.friendface.post.PostRepository;
import com.scottlogic.grad_training.friendface.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  public ResponseEntity<String> like(String sessionToken, int postId) {
    User user = sessionService.validateSession(sessionToken);
    if(user == null){
      // No session token for user
      return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
    }
    if(postRepository.existsById(postId)){
      Post post = postRepository.getReferenceById(postId);
      List<Like> likes = likeRepository.findByPostIdAndUserId(postId, user.getId());
      if(!likes.isEmpty()){
        // Send ok if like entry deleted
        likeRepository.delete(likes.getFirst());
        return new ResponseEntity<>(HttpStatus.OK);
      }else{
        // Send created if like entry created
        likeRepository.save(new Like(post,user));
        return new ResponseEntity<>(HttpStatus.CREATED);
      }
    }
    // post not found
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<Integer> getPostLikes(int postId) {
    if(!postRepository.existsById(postId)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }else{
      return new ResponseEntity<>(likeRepository.findByPostId(postId).size(), HttpStatus.OK);
    }
  }

  public ResponseEntity<List<Integer>> getUserLikes(int postId) {
    if(!postRepository.existsById(postId)){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }else{
      return new ResponseEntity<>(likeRepository.findUserIdByPostId(postId), HttpStatus.OK);
    }
  }

  public int getTotalUserLikes(int userId){
    //TODO add some error handling stuff to this section
    return likeRepository.findTotalUserLikes(userId);
  }
}
