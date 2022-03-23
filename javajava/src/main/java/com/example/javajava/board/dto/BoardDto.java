package com.example.javajava.board.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private List<FileDto> fileList;
}
