package com.example.javajava.board.dto;

import lombok.Data;

@Data
public class FileDto {
    private Long idx;
    private Long boardIdx;
    private String originalFileName;
    private String storedFilePath;
    private Long fileSize;
}
