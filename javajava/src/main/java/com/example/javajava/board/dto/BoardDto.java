package com.example.javajava.board.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

import com.example.javajava.board.entity.Board;

// DTO : Data Transfer Object : 데이터 전달 객체 (= DB 컬럼)
@Data
// @Getter @Setter @ToString
public class BoardDto {

    private Long boardIdx;
    private String title;
    private String contents;
    private int hitCnt;
    private String creatorId;
    private Date createDatetime;

    // 파일 관리를 위한 리스트 추가
    private List<FileDto> fileList;

    public BoardDto(Board board) {
        this.boardIdx = board.getIdx();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.hitCnt = board.getHitCnt();
        this.creatorId = board.getCreatorId();
        this.createDatetime = board.getCreateDatetime();
    }
}
