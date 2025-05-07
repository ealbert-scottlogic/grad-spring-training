package com.scottlogic.grad_training.friendface.Sessions;

import com.scottlogic.grad_training.friendface.user.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TokenAuthFilter extends OncePerRequestFilter {
  private final SessionService sessionService;

  public TokenAuthFilter(SessionService sessionService) {
    this.sessionService = sessionService;
  }
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain)
          throws ServletException, IOException {
    System.out.println(request.getHeader("Authorization"));
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      User user = sessionService.validateSession(token);
      if (user  != null) {
        UsernamePasswordAuthenticationToken auth =
          new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }
    filterChain.doFilter(request, response);
  }}
