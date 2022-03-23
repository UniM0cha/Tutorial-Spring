package com.example.javajava.board.dto;

import com.example.javajava.board.entity.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class FileDto {
  private Long idx;
  private Long boardIdx;
  private String originalFileName;
  private String storedFilePath;
  private Long fileSize;

  public FileDto(File file) {
    this.idx = file.getIdx();
    this.originalFileName = file.getOriginalFileName();
    this.storedFilePath = file.getStoredFilePath();
    this.fileSize = file.getFileSize();
  }
}
