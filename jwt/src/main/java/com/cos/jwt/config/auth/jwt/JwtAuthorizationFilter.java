package com.cos.jwt.config.auth.jwt;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.config.auth.PrincipalDetails;
import com.cos.jwt.model.User;
import com.cos.jwt.repository.UserRepository;

// 시큐리티가 필터를 가지고 있는데 그 필터 중에 BasicAuthenticationFilter 라는 것이 있음.
// 이 필터는 권한이나 인증이 필요한 특정 주소를 요청했을 때 위의 필터를 무조건 타게 되어있다.
// 만약 권한이나 인증이 필요한 주소가 아니라면 이 필터를 타지 않는다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private UserRepository userRepository;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
    super(authenticationManager);
    this.userRepository = userRepository;

  }

  // 인증이나 권한이 필요한 주소 요청이 있을 때 해당 필터를 타게 된다.
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    super.doFilterInternal(request, response, chain);
    System.out.println("인증이나 권한이 필요한 주소 요청이 됨");

    String jwtHeader = request.getHeader("Authorization");
    System.out.println("jwtHeader : " + jwtHeader);

    // JWT토큰을 검증해서 정상적인 사용자인지 확인
    if (jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
      chain.doFilter(request, response);
      return;
    }

    String token = request.getHeader("Authorization").replace("Bearer ", "");

    String username = JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("username").asString();

    // 서명이 정상적으로 됨
    if (username != null) {
      User userEntity = userRepository.findByUsername(username);

      PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
      // JWT 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
      Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null);

      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(request, response);
    }
  }
}
