package com.example.securityoauth2.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Member {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(length = 255, nullable = false)
  private String name;

  @Column(length = 255, nullable = false, unique = true)
  private String account;

  @Column(length = 255, nullable = false)
  private String password;

  @Column(name = "last_access_dt")
  private LocalDateTime lastAccessDt;

  @Column(name = "reg_dt")
  private LocalDateTime regDt;

  public Member(Integer id, String name, String account, String password) {
    this.id = id;
    this.name = name;
    this.account = account;
    this.password = password;
  }
}
