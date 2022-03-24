package com.example.javajava.board.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@ToString
@Slf4j
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

  private String creatorId = "test";

  private char deleteYn = 'N';

  @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
  private List<File> files = new ArrayList<>();

  public void updateHitcnt() {
    this.hitCnt++;
    return;
  }

  public void boardDtoToBoard(BoardDto boardDto) {
    this.idx = boardDto.getIdx();
    this.title = boardDto.getTitle();
    this.contents = boardDto.getContents();
    this.hitCnt = boardDto.getHitCnt();
    this.creatorId = boardDto.getCreatorId();
    this.createDatetime = boardDto.getCreateDatetime();
  }

  public void deleteBoard() {
    this.deleteYn = 'Y';
  }

  // public void addFile(File file) {
  // // board.files.add(file)과 같은 의미다.
  // this.files.add(file);

  // // 파일쪽에서 보드를 가져왔는데 현재 보드가 아니면
  // if (file.getBoard() != this) {
  // // 파일쪽의 보드를 자신으로 갈아끼운다.
  // file.setBoard(this);
  // // 이 말인 즉슨, 파일쪽에서 보드를 가져왔는데 현재 이 보드이면
  // // 추가를 하지 않겠다는 뜻이다.
  // }
  // }
}