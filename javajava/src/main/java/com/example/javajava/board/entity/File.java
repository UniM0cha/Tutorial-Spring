package com.example.javajava.board.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.javajava.board.dto.FileDto;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class File {
  @Id
  @GeneratedValue
  @Column(name = "file_idx")
  private Long idx;

  private String originalFileName;
  private String storedFilePath;
  private Long fileSize;
  private String creatorId = "test";

  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createDatetime;

  private char deleteYn = 'N';

  @ManyToOne(fetch = FetchType.LAZY)
  private Board board;

  public File(FileDto fileDto) {
    this.originalFileName = fileDto.getOriginalFileName();
    this.storedFilePath = fileDto.getStoredFilePath();
    this.fileSize = fileDto.getFileSize();
  }

  public void setBoard(Board board) {
    // 양방향 매핑 버려
    this.board = board;
  }
  // public void setBoard(Board board) {
  // // 보드가 널이 아니라면, 즉 이미 관계가 맺어져 있다면
  // if (this.board != null) {
  // // 전에 보드쪽에 있었던 관계는 끊어준다.
  // this.board.getFiles().remove(this);
  // }
  // // 그다음에, 현재 보드와 관계를 맺어준다.
  // this.board = board;

  // // 보드쪽에서 파일리스트를 가져오고, 그 리스트 안에 현재 파일이 들어있지 않다면,
  // if (!board.getFiles().contains(this)) {
  // // 이 파일을 넣어주는데,
  // board.addFile(this);
  // // 이 말인 즉슨, 이 파일이 들어있다면 파일을 넣지 않겠다는 뜻
  // }
  // }

}