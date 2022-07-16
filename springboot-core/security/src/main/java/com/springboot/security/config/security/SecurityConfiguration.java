package com.springboot.security.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable()
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        .and()
        .authorizeRequests()
        .antMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll()
        .antMatchers(HttpMethod.GET, "/product/**").permitAll()
        .antMatchers("**exception**").permitAll()
        .anyRequest().hasRole("ADMIN")

        .and()
        .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())

        .and()
        .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())

        .and()
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/webjars/**",
        "/swagger/**",
        "/sign-api/exception");
  }
}
