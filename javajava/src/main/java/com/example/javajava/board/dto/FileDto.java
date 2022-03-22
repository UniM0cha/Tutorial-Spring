package com.example.javajava.board.dto;

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
}
