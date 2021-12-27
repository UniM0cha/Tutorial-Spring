package kr.inhatc.spring.board.service;

import kr.inhatc.spring.board.dto.BoardDto;
import kr.inhatc.spring.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardDto> boardList() {
        return boardMapper.boardList();
    }
}
