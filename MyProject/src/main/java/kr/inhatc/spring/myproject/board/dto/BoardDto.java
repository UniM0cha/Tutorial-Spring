package kr.inhatc.spring.myproject.board.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

// DTO : Data Transfer Object : 데이터 전달 객체 (= DB 컬럼)
@Data
// @Getter @Setter @ToString
public class BoardDto {

    private int boardIdx;
    private String title;
    private String contents;
    private int hitCnt;
    private String creatorId;
    private String createdDatetime;
}
