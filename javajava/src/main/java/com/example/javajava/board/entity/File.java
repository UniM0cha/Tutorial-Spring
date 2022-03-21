package com.example.javajava.board.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;

@Entity
@Getter
public class File {
  @Id
  @GeneratedValue
  private Long idx;

  private String originalFileName;
  private String storedFilePath;
  private Long fileSize;
  private String creatorId;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createDatetime;

  private char deleteYn = 'N';

}