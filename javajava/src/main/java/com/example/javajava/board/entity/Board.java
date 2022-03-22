package com.example.javajava.board.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.javajava.board.dto.BoardDto;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Board {
  @Id
  @Column(name = "board_idx")
  @GeneratedValue
  private Long idx;

  private String title;
  private String contents;
  private int hitCnt = 0;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createDatetime;

  private String creatorId;

  private char deleteYn = 'N';

  @OneToMany(mappedBy = "board")
  private List<File> files = new ArrayList<>();

  public void updateHitcnt() {
    this.hitCnt++;
    return;
  }

  public void boardDtoToBoard(BoardDto boardDto) {
    this.idx = boardDto.getBoardIdx();
    this.title = boardDto.getTitle();
    this.contents = boardDto.getContents();
    this.hitCnt = boardDto.getHitCnt();
    this.creatorId = boardDto.getCreatorId();
    this.createDatetime = boardDto.getCreateDatetime();
  }
}