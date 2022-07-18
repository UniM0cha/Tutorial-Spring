package com.cos.jwt.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.cos.jwt.config.auth.jwt.JwtAuthenticationFilter;
import com.cos.jwt.config.auth.jwt.JwtAuthorizationFilter;
import com.cos.jwt.filter.MyFilter1;
import com.cos.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
@RequiredArgsConstructor
public class SecurityConfig {

  private final CorsFilter corsFilter;
  private final UserRepository userRepository;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http.addFilter(new MyFilter1()); // 시큐리티 필터를 넣을거면 다른 함수를 써라! 라고 오류 발생
    // http.addFilterBefore(new MyFilter1(),
    // UsernamePasswordAuthenticationFilter.class);

    http.csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 쓰지 않겠다.

    http.formLogin().disable();

    http.httpBasic().disable(); // Bearer 방식을 쓰기 위해 원래 쓰고 있던 HttpBasic 방식을 비활성화 한다.
                                // (HttpBasic은 Https가 아닌 이상 비밀번호가 노출된다.)

    http.addFilter(corsFilter); // @CrossOrigin(인증이 없을 때), 시큐리티 필터에 등록(인증 가능)
    http.apply(new MyCustomDsl());

    http.authorizeRequests(authorize -> authorize
        .antMatchers("/api/v1/user/**")
        .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
        .antMatchers("/api/v1/manager/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
        .antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
        .anyRequest().permitAll());

    return http.build();

  }

  public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity builder) throws Exception {
      AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
      builder.addFilter(new JwtAuthenticationFilter(authenticationManager))
          .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
    }
  }
}
