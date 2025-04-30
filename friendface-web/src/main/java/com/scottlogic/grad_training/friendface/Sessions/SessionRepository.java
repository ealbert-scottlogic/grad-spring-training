package com.scottlogic.grad_training.friendface.Sessions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Integer> {
  List<Session> findBySessionToken(String session_token);
}
