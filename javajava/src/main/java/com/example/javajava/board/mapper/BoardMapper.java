package com.example.javajava.board.mapper;

import com.example.javajava.board.dto.BoardDto;
import com.example.javajava.board.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 메소드의 이름과 쿼리의 이름을 동일하게 처리
    List<BoardDto> boardList();

    void boardInsert(BoardDto board);

    BoardDto boardDetail(int boardIdx);

    void boardUpdate(BoardDto board);

    void updateHit(int boardIdx);

    void boardDelete(int boardIdx);

    List<FileDto> selectBoardFileList(int boardIdx);

    void boardFileInsert(List<FileDto> list);

    FileDto selectFileInfo(@Param("idx") int idx, @Param("boardIdx") int boardIdx);
}
