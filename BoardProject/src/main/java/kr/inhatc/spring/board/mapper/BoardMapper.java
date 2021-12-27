package kr.inhatc.spring.board.mapper;

import kr.inhatc.spring.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto> boardList();
}
