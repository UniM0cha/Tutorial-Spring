package kr.inhatc.spring.myproject.board.mapper;

import kr.inhatc.spring.myproject.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 메소드의 이름과 쿼리의 이름을 동일하게 처리
    List<BoardDto> boardList();
}
