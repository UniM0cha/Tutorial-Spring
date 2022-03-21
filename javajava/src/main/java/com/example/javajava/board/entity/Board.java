package com.example.javajava.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BOARD")
public class Board {
  @Id
  @Column(name = "board_idx")
  private int idx;

  private String title;
  private String contents;
  private int hitCnt;
  private Date createDatetime;
  private String creatorId;
  private char deleteYn;
}