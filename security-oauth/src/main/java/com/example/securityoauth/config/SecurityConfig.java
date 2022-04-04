package com.example.securityoauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        // /chk 경로는 모두 허용한다.
        .antMatchers("/chk").permitAll()
        // /manage 경로는 ADMIN 역할을 가진 사람만 접근 가능하다.
        // ROLE은 DB에 넣어두면 된다.
        .antMatchers("/manage").hasAuthority("ROLE_ADMIN")
        // 어느 URL로 접근하던지 인증이 필요함
        .anyRequest()
        .authenticated()

        // login 페이지를 추가하고 성공할 시에 이동할 경로 지정
        .and()
        .formLogin()
        // 커스텀 로그인 페이지 지정
        .loginPage("/view/login")
        // 별도로 컨트롤러를 만드는 것이 아닌, form으로 로그인 할 때 action 값에 넣어줄 경로
        .loginProcessingUrl("/loginProc")
        // 아이디와 패스워드 파라미터 지정. default 값은 username과 password
        .usernameParameter("id")
        .passwordParameter("pw")
        .defaultSuccessUrl("/view/dashboard", true)
        .permitAll()

        // 로그아웃 기능도 추가
        .and()
        .logout()
        // 로그아웃을 할 경로 지정. default는 /logout
        .logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc"));

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // 어느 경로든 인증이 필요한 설정이기 때문에 에셋 경로는 제외시켜주어야 한다.
    web.ignoring().antMatchers("/static/js/**", "/static/css/**", "/static/img/**", "/static/frontend/**");
  }
}
