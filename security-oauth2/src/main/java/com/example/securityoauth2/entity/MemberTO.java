package com.example.securityoauth2.entity;

import java.time.LocalDateTime;

public class MemberTO {
  private Integer id;

  private String name;

  private String account;

  private String password;

  private LocalDateTime lastAccessDt;

  private LocalDateTime regDt;

  public Member toEntity() {
    return new Member(id, name, account, password);
  }
}
