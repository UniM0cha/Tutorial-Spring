package com.example.securityoauth.auth;

import com.example.securityoauth.user.User;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// id, pw에 따른 인증을 처리하기 위한 UserDetailsService 구현체
@Service
public class LoginIdPwValidator implements UserDetailsService {

  // 로그인시에 사용자가 입력한 id가 loadUserByUsername의 인자로 들어온다.
  // 들어온 인자로 DB에서 select 해준다.
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return null;
  }

  // 암호를 어떻게 암호화 할지 설정
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
