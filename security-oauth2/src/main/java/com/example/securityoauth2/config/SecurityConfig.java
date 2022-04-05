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

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers("/member/**").authenticated()
        .antMatchers("/admin/**").authenticated()
        .antMatchers("/**").permitAll();

    http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll();

    http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login")
        .invalidateHttpSession(true);

    http.exceptionHandling()
        .accessDeniedPage("/denied");
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
  }
}
