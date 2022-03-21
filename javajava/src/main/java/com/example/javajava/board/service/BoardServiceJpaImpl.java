package com.example.javajava.board.service;

import com.example.javajava.board.dto.BoardDto;
import com.example.javajava.board.dto.FileDto;
import com.example.javajava.board.mapper.BoardMapper;
import com.example.javajava.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class BoardServiceJpaImpl {

    private BoardMapper boardMapper;
    private FileUtils fileUtils;

    @Autowired
    public BoardServiceJpaImpl(BoardMapper boardMapper, FileUtils fileUtils) {
        this.boardMapper = boardMapper;
        this.fileUtils = fileUtils;
    }

    @Override
    public List<BoardDto> boardList() {
        return boardMapper.boardList();
    }

    @Override
    public void boardInsert(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) {
        boardMapper.boardInsert(board);

        // // 임시
        // if(!ObjectUtils.isEmpty(multipartHttpServletRequest)) {
        // Iterator<String> iter = multipartHttpServletRequest.getFileNames();
        //
        // while (iter.hasNext()) {
        // String name = iter.next();
        // List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
        // for (MultipartFile multipartFile : list) {
        // System.out.println("=====================>file name = " +
        // multipartFile.getOriginalFilename());
        // System.out.println("=====================>file size = " +
        // multipartFile.getSize());
        // System.out.println("=====================>file type = " +
        // multipartFile.getContentType());
        // }
        // }
        // }

        // 1. 파일 저장
        List<FileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);

        // 2. DB 저장
        // list에 저장된 내용물이 있다면
        if (!CollectionUtils.isEmpty(list)) {
            boardMapper.boardFileInsert(list);
        }

    }

    @Override
    public BoardDto boardDetail(int boardIdx) {
        BoardDto board = boardMapper.boardDetail(boardIdx);

        // 파일정보
        List<FileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
        board.setFileList(fileList);

        // 조회수 증가
        boardMapper.updateHit(boardIdx);
        return board;
    }

    @Override
    public void boardUpdate(BoardDto board) {
        boardMapper.boardUpdate(board);
    }

    @Override
    public void boardDelete(int boardIdx) {
        boardMapper.boardDelete(boardIdx);
    }

    @Override
    public FileDto selectFileInfo(int idx, int boardIdx) {
        FileDto boardFile = boardMapper.selectFileInfo(idx, boardIdx);
        return boardFile;
    }
}
