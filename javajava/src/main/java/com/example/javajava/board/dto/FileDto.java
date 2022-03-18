package com.example.javajava.board.dto;

import lombok.Data;

@Data
public class FileDto {
    private int idx;
    private int boardIdx;
    private String originalFileName;
    private String storedFilePath;
    private long fileSize;
}
