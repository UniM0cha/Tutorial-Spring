package com.example.javajava.board.service;

import com.example.javajava.board.dto.BoardDto;
import com.example.javajava.board.dto.FileDto;
import com.example.javajava.board.entity.Board;
import com.example.javajava.board.entity.File;
import com.example.javajava.board.mapper.BoardMapper;
import com.example.javajava.board.repository.BoardRepository;
import com.example.javajava.board.repository.FileRepository;
import com.example.javajava.utils.FileUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BoardServiceJpaImpl implements BoardService {

  private BoardRepository boardRepository;
  private FileRepository fileRepository;
  private FileUtils fileUtils;
  private ModelMapper modelMapper;

  @Autowired
  public BoardServiceJpaImpl(BoardRepository boardRepository, FileRepository fileRepository, FileUtils fileUtils,
      ModelMapper modelMapper) {
    this.boardRepository = boardRepository;
    this.fileRepository = fileRepository;
    this.fileUtils = fileUtils;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<BoardDto> boardList() {
    List<Board> boardList = boardRepository.findAll();
    List<BoardDto> boardDtoList = boardList.stream()
        .map((board) -> modelMapper.map(board, BoardDto.class))
        .collect(Collectors.toList());
    return boardDtoList;
  }

  @Override
  public void boardInsert(BoardDto boardDto, MultipartHttpServletRequest multipartHttpServletRequest) {
    Board board = modelMapper.map(boardDto, Board.class);
    log.info("저장할 board: " + board);
    Board savedBoard = boardRepository.save(board);

    // 1. 파일 저장
    List<FileDto> fileDtos = fileUtils.parseFileInfo(savedBoard.getIdx(), multipartHttpServletRequest);

    // 2. DB 저장
    // list에 저장된 내용물이 있다면
    if (!CollectionUtils.isEmpty(fileDtos)) {
      List<File> files = fileDtos.stream()
          .map((fileDto) -> modelMapper.map(fileDto, File.class))
          .collect(Collectors.toList());

      for (File file : files) {
        log.info("저장할 files: " + file);
      }
      fileRepository.saveAll(files);
    }

  }

  @Override
  public BoardDto boardDetail(Long boardIdx) {
    Board board = boardRepository.findById(boardIdx).get();

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
