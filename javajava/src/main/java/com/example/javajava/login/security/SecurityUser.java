package com.example.javajava.login.security;

import java.util.Collection;

import com.example.javajava.user.entity.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {

  private Users user;

  public SecurityUser(Users user) {
    // 암호화처리 전까지는 패스워드 앞에 {noop} 추가
    super(user.getId(), user.getPw(), AuthorityUtils.createAuthorityList(user.getRole()));
    this.user = user;
  }

}