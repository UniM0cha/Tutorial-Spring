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
    this.board = board;
  }
}