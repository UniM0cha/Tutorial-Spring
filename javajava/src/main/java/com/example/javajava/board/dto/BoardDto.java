package com.example.javajava.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.javajava.board.entity.Board;
import com.example.javajava.board.entity.File;

// DTO : Data Transfer Object : 데이터 전달 객체 (= DB 컬럼)
@Data
@NoArgsConstructor
// @Getter @Setter @ToString
public class BoardDto {
  private Long idx;
  private String title;
  private String contents;
  private int hitCnt;
  private String creatorId;
  private Date createDatetime;

  // 파일 관리를 위한 리스트 추가
  private List<FileDto> fileList = new ArrayList<>();

  public BoardDto(Board board) {
    this.idx = board.getIdx();
    this.title = board.getTitle();
    this.contents = board.getContents();
    this.hitCnt = board.getHitCnt();
    this.creatorId = board.getCreatorId();
    this.createDatetime = board.getCreateDatetime();
    for (File file : board.getFiles()) {
      this.fileList.add(new FileDto(file));
    }
  }
}
