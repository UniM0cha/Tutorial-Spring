package com.project.project8core.config.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

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
  private String secretKey = "secretKeysssssssssssssssssssssssssssssssss";
  private Key JWT_SECRET;

  private static final int JWT_EXPIRATION_MS = 604800000;

  @PostConstruct
  protected void init() {
    JWT_SECRET = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  // 컨트롤러에서 받아온 사용자 정보로 JWT 토큰 생성
  public String generateToken(String uid, List<String> roles) {
    log.info("주어진 정보로 토큰 만드는 중, uid : {}, roles : {}", uid, roles);
    Claims claims = Jwts.claims().setSubject(uid);
    claims.put("roles", roles);
    Date now = new Date();
    Date expireDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
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
      log.error("유효하지 않은 JWT 입니다.");
    } catch (ExpiredJwtException ex) {
      log.error("만료된 JWT 입니다.");
    } catch (UnsupportedJwtException ex) {
      log.error("지원되지 않는 JWT 입니다.");
    } catch (IllegalArgumentException ex) {
      log.error("JWT가 없습니다.");
    }
    return false;
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsernameFromJwt(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

}
