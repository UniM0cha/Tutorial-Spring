package com.example.securityoauth2.config;

import com.example.securityoauth2.service.MemberService;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private MemberService memberService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // WebSecurity는 FilterChainProxy를 생성하는 필터
  // 다양한 Filter 설정을 적용할 수 있음
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
  }

  // HttpSecurity를 통해 HTTP 요청에 대한 보안을 설정할 수 있음
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // 모든 사용자가 /** 경로로 요청할 수 있지만
    // /member/**, /admin/** 경로는 인증된 사용자만 요청이 가능함
    http.authorizeRequests()
        .antMatchers("/member/**").authenticated()
        .antMatchers("/admin/**").authenticated()
        .antMatchers("/**").permitAll();

    // 로그인 설정
    http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        // 로그인 페이지는 모든 유저에게 접근 허용
        .permitAll();

    // 로그아웃 설정
    http.logout()
        // /logout 경로로 요청하면 로그아웃 수행
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // 로그아웃 성공하면 로그인 페이지로 이동
        .logoutSuccessUrl("/login")
        // 로그아웃 성공 시 세션을 제거
        .invalidateHttpSession(true);

    // 예외처리
    http.exceptionHandling()
        // 권한이 없는 사용자가 접근했을 시 이동할 경로 지정
        .accessDeniedPage("/denied");
  }

  // 사용자 인증을 담당하는 AuthenticationManager를 생성함
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
  }
}
