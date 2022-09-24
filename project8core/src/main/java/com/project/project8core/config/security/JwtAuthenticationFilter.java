package com.project.project8core.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String jwt = getJwtFromRequest(request); // request에서 jwt 토큰을 꺼낸다.
    log.info("JWT 추출 완료, token : {}", jwt);

    log.info("JWT 값 검증");
    if (jwtTokenProvider.validateToken(jwt)) {

      log.info("JWT 값 검증 완료");
      Authentication authentication = jwtTokenProvider.getAuthentication(jwt); // jwt로부터 Authentication 객체를 받아온다.
      SecurityContextHolder.getContext().setAuthentication(authentication); // securityContext에 Authentication 등록
      log.info("JWT로부터 UserDetails을 Authentication에 등록 완료");

    }

    filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
    // Authorization 헤더에서 bearer 토큰을 받아옴
    String bearerToken = request.getHeader("Authorization");

    // 토큰이 있는지, Bearer로 시작하는지 확인
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      // 그 후 토큰값만 잘라서 리턴
      return bearerToken.substring("Bearer ".length());
    }
    return null;
  }
}
