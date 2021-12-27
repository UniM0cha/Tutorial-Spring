package com.example.firstproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
public class Article {

  // 엔티티에는 대표값을 넣어주어야 함. 주민등록번호 같은 것
  @Id
  @GeneratedValue   // 1,2,3,... 자동 생성 어노테이션
  private Long id;

  @Column // 필드 추가
  private String title;

  @Column
  private String content;

}
