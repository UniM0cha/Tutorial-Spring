package com.cos.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.security1.model.User;

import lombok.Data;

/**
 * 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
 * 
 * 로그인 진행이 완료되면 시큐리티 session을 만들어준다. (Security ContextHolder)
 * Authentication 타입의 객체를 가지고 있는다.
 * Authentication 안에 User정보가 있어야 한다.
 * User오브젝트의 타입은 UserDetails 타입의 객체
 * 
 * Security Session => Authentication => UserDetails
 */

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

  private User user; // 콤포지션
  private Map<String, Object> attributes;

  // 일반 로그인에 쓰는 생성자
  public PrincipalDetails(User user) {
    this.user = user;
  }

  // OAuth 로그인에 쓰는 생성자
  public PrincipalDetails(User user, Map<String, Object> attributes) {
    this.user = user;
    this.attributes = attributes;
  }

  // 해당 User의 권한을 리턴하는 곳
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();
    collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return user.getRole();
      }
    });
    return collection;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    /**
     * 자신의 사이트에서 1년동안 로그인을 하지 않으면 휴면계정으로 돌리기로 했을 때,
     * User에다가 로그인날짜 속성을 넣어서 현재 날짜랑 비교 후 false로 만들면 된다.
     */

    return true;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public String getName() {
    // return attributes.get("sub").toString();
    return null;
  }

}
