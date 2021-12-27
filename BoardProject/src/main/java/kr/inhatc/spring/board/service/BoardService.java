package kr.inhatc.spring.board.service;

import kr.inhatc.spring.board.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {

    List<BoardDto> boardList();
}
