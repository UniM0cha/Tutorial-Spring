package com.example.javajava.login.service;

import java.util.Optional;

import com.example.javajava.login.security.SecurityUser;
import com.example.javajava.user.entity.Users;
import com.example.javajava.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecurityUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Users> optional = userRepository.findById(username);
    if (optional.isPresent()) {
      Users user = optional.get();
      log.info("==================>" + user);
      return new SecurityUser(user);
    }
    return null;
  }

}