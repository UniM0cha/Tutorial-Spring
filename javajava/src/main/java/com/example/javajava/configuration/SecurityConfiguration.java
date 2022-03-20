package com.example.javajava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 사용자 접근 경로 설정
    http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/user/**").hasAnyRole("MEMBER", "ADMIN")
        .antMatchers("/board/**").hasRole("ADMIN");

    // CSRF : Cross Site Request Forgery : 사이트 간 위조 요청
    // 거의 필수적이지만 개발 편의성 문제로 개발 중에는 비활성화해둔다.
    http.csrf().disable();

    // 로그인 페이지와 성공시 이동할 페이지 설정
    http.formLogin().loginPage("/login/login").defaultSuccessUrl("/", true);

    // 실패시 이동할 페이지
    http.exceptionHandling().accessDeniedPage("/login/accessDenied");

    // 로그아웃 요청시 세션을 강제 종료하고 시작페이지로 이동
    http.logout().logoutUrl("/login/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
  }

  // 패스워드 암호화 처리
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}