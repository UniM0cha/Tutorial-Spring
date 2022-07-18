package com.springboot.jwtself.config.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  private final UserDetailsService userDetailsService;

  @Value("${springboot.jwt.secret}")
  private String secretKey = "secretKeysssssssssssssssssssssss";
  private static Key JWT_SECRET;

  private static final int JWT_EXPIRATION_MS = 604800000;

  @PostConstruct
  protected void init() {
    log.info("이거 실행되기는 함? : " + secretKey);
    JWT_SECRET = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  // 컨트롤러에서 받아온 Authentication 객체에서 사용자 정보를 뽑아내 JWT 토큰 생성
  public String generateToken(Authentication authentication) {
    Date now = new Date();
    Date expireDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
    Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    return Jwts.builder()
        .setSubject((String) authentication.getPrincipal())
        .setIssuedAt(new Date())
        .setExpiration(expireDate)
        .signWith(JWT_SECRET, SignatureAlgorithm.HS512)
        .compact();
  }

  // JWT 토큰에서 아이디 추출
  public String getUsernameFromJwt(String token) {
    Claims claims = (Claims) Jwts.parserBuilder()
        .setSigningKey(JWT_SECRET)
        .build()
        .parse(token)
        .getBody();

    return claims.getSubject();
  }

  // jwt 토큰의 유효성 검사
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(JWT_SECRET).build().parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsernameFromJwt(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

}
