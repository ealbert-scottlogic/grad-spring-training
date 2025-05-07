package com.scottlogic.grad_training.friendface;

import com.scottlogic.grad_training.friendface.Sessions.SessionService;
import com.scottlogic.grad_training.friendface.Sessions.TokenAuthFilter;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Autowired
  private SessionService sessionService;
  @Bean
  public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
    return http
            .cors(Customizer.withDefaults())
            .csrf(csrf-> csrf.disable())
            .authorizeHttpRequests(auth ->
            auth.requestMatchers("/userManagement/createUser",
                    "/userManagement/login").permitAll()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(new TokenAuthFilter(sessionService), UsernamePasswordAuthenticationFilter.class)
            .build();
  }
  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    UserDetails user = User.builder()
            .username("swagger")
            .password(encoder.encode("swagger123"))  // BCrypt-hashed password
            .roles("USER")
            .build();
    return new InMemoryUserDetailsManager(user);
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
