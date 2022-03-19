package com.example.javajava.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 사용자 접근 경로 설정
    http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/user/**").hasRole("MEMBER")
        .antMatchers("/board/**").hasRole("ADMIN");

    // CSRF : Cross Site Request Forgery : 사이트 간 위조 요청
    // 거의 필수적이지만 개발 편의성 문제로 개발 중에는 비활성화해둔다.
    http.csrf().disable();

    // 로그인 페이지와 성공시 이동할 페이지 설정
    http.formLogin().loginPage("/login/login").defaultSuccessUrl("/", true);
  }
}