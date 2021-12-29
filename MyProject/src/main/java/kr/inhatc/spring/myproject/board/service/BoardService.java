package kr.inhatc.spring.myproject.board.service;

import kr.inhatc.spring.myproject.board.dto.BoardDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
    List<BoardDto> boardList();

    void boardInsert(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest);

    BoardDto boardDetail(int boardIdx);

    void boardUpdate(BoardDto board);

    void boardDelete(int boardIdx);
}
