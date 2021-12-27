package kr.inhatc.spring.myproject.board.service;

import kr.inhatc.spring.myproject.board.dto.BoardDto;
import kr.inhatc.spring.myproject.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;
    // 나중에 클래스 주입으로 바꿔보자

    @Override
    public List<BoardDto> boardList() {
        return boardMapper.boardList();
    }
}
