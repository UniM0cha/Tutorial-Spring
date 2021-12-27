package kr.inhatc.spring.myproject.board.service;

import kr.inhatc.spring.myproject.board.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> boardList();
}
