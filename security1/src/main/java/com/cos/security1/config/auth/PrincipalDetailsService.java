package com.cos.security1.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * 시큐리티 설정에서 loginProcessingUrl("/login");
 * /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함수가 실행
 */

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  // 시큐리티 세션에 들어가는 것은 Authentication 타입. 그 안에 UserDetails을 넣어주어야 한다.
  // 시큐리티 세션(내부 Authentication(내부 UserDetails))

  // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User userEntity = userRepository.findByUsername(username);
    if (userEntity != null) {
      return new PrincipalDetails(userEntity);
    }
    return null;
  }

}
