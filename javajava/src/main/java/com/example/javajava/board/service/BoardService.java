package com.example.javajava.board.service;

import com.example.javajava.board.dto.BoardDto;
import com.example.javajava.board.dto.FileDto;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
    List<BoardDto> boardList();

    void boardInsert(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest);

    BoardDto boardDetail(int boardIdx);

    void boardUpdate(BoardDto board);

    void boardDelete(int boardIdx);

    FileDto selectFileInfo(int idx, int boardIdx);
}
