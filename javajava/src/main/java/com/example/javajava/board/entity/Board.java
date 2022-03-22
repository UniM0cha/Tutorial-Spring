package com.example.javajava.board.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;

@Entity
@Getter
public class Board {
  @Id
  @Column(name = "board_idx")
  private Long idx;
  private String title;
  private String contents;
  private int hitCnt = 0;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createDatetime;

  private String creatorId;

  private char deleteYn = 'N';

  @OneToMany(mappedBy = "board")
  private List<File> files = new ArrayList<>();

  public void updateHitcnt() {
    this.hitCnt++;
    return;
  }
}