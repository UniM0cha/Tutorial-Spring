package com.springboot.jwtself.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final CorsFilter corsFilter;
  private final JwtTokenProvider jwtTokenProvider;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf().disable(); // csrf 비활성화

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 비활성화

    http.formLogin().disable(); // formLogin 비활성화

    http.httpBasic().disable(); // 기존 인증 방식 비활성화

    http.addFilter(corsFilter); // CORS 필터 적용

    // UsernamePasswordAuthenticationFilter 전에(앞에) JWT 필터 적용
    http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

    // 경로 보안 설정
    http.authorizeRequests(authorize -> authorize
        .antMatchers("/api/v1/user/**")
        .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
        .antMatchers("/api/v1/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
        .antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
        .anyRequest().permitAll());

    return http.build();
  }

}
