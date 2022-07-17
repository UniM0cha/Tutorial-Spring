package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.security1.config.oauth.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
@RequiredArgsConstructor
public class SecurityConfig {

  private final PrincipalOauth2UserService principalOauth2UserService;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.formLogin(form -> form
        .loginPage("/loginForm") // 로그인이 필요할 때에 이동할 경로
        .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행
        .defaultSuccessUrl("/"));

    http.authorizeRequests(authrize -> authrize
        .antMatchers("/user/**").authenticated()
        .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        .anyRequest()
        .permitAll());

    http.oauth2Login(oauth2 -> oauth2
        .loginPage("/loginForm")
        /**
         * 구글 로그인이 완료된 후의 후처리가 필요함. - 엑세스토큰과 사용자프로필정보를 한번에 받아옴
         * 1. 코드 받기 (인증)
         * 2. 엑세스토큰 받기(권한)
         * 3. 사용자 프로필 정보를 가져옴
         * 4. 정보를 토대로 회원가입을 자동으로 진행시키거나 추가 정보를 입력하게 함.
         */
        .userInfoEndpoint()
        .userService(principalOauth2UserService));

    return http.build();

  }
}
