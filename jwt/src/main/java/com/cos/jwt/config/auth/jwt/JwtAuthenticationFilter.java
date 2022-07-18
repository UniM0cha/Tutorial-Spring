package com.cos.jwt.config.auth.jwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.jwt.config.auth.PrincipalDetails;
import com.cos.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

// 스프링 시큐리티에 UsernamePasswordAuthenticationFilter 필터가 있음.
// /login 요청을 해서 username이나 password를 post로 전송하면
// 해당 필터가 작동한다.

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    System.out.println("JwtAuthenticationFilter : 로그인 시도중");

    /**
     * 1. username, password 를 받아서
     * 
     * 2. 정상인지 로그인 시도를 해본다.
     * authenticationManager으로 로그인 시도를 하면
     * PrincipalDetailsService가 호출되며 loadUserByUsername()이라는 함수 실행됨.
     * 
     * 3. PrincipalDetails를 세션에 담고
     * 
     * 4. JWT 토큰을 만들어서 응답해주면 됨.
     */

    // 1.
    try {
      // BufferedReader br = request.getReader();

      // String input = null;
      // while ((input = br.readLine()) != null) {
      // System.out.println(input);
      // }
      ObjectMapper om = new ObjectMapper();
      User user = om.readValue(request.getInputStream(), User.class); // Json을 파싱해서 오브젝트로 만들어준다.
      System.out.println(user);

      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          user.getUsername(), user.getPassword());

      Authentication authentication = authenticationManager.authenticate(authenticationToken);

      PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
      System.out.println("로그인 완료됨 : " + principalDetails.getUser().getUsername());

      // authentication 객체가 session 영역에 저장을 해야하고 그 방법은 return
      // 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는 것임.
      // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없지만, 단지 권한 처리 때문에 session을 넣어주는 것.

      // JWT 토큰을 만듦
      return authentication;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  // attemptAuthentication 실행 후 인증이 정상적으로 되었으면, successfulAuthentication 함수가 실행된다.
  // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 된다.
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
    System.out.println("successfulAuthentication 실행됨 : 인증이 완료되었다는 말임.");

    PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

    String jwtToken = JWT.create()
        .withSubject(principalDetails.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + (60000 * 10)))
        .withClaim("id", principalDetails.getUser().getId())
        .withClaim("username", principalDetails.getUser().getUsername())
        .sign(Algorithm.HMAC512("cos"));

    response.addHeader("Authorization", "Bearer " + jwtToken);
    super.successfulAuthentication(request, response, chain, authResult);
  }
}
