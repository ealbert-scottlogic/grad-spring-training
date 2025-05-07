package com.scottlogic.grad_training.friendface.Sessions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer> {
  Optional<Session> findBySessionToken(String session_token);
  List<Session> findByUserId(int  user_id);
}
