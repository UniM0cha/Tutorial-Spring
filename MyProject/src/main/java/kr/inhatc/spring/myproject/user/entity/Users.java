package kr.inhatc.spring.myproject.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
// 디폴트 생성자
@NoArgsConstructor
@Data
public class Users {

  @Id
  @Column(name = "USER_ID")
  private String id;
  private String pw;
  private String name;
  private String email;

  // Timestamp 디폴트 값 설정
  // readonly, sysdate를 default로 사용
  @Temporal(TemporalType.TIMESTAMP)
  @Column(insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
  private Date joinDate;

  private String enabled;
  private String role;
}