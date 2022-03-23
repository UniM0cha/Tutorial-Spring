package com.example.javajava.board.service;

import com.example.javajava.board.dto.BoardDto;
import com.example.javajava.board.dto.FileDto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    List<BoardDto> boardList();

    void boardInsert(BoardDto board, List<MultipartFile> files);

    BoardDto boardDetail(Long boardIdx);

    void boardUpdate(BoardDto board);

    void boardDelete(Long boardIdx);

    FileDto selectFileInfo(Long idx, Long boardIdx);
}
