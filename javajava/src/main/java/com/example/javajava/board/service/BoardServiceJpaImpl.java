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
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BoardServiceJpaImpl implements BoardService {

  private BoardRepository boardRepository;
  private BoardMapper boardMapper;
  private FileRepository fileRepository;
  private FileUtils fileUtils;
  private ModelMapper modelMapper;

  @Autowired
  public BoardServiceJpaImpl(
      BoardRepository boardRepository,
      BoardMapper boardMapper,
      FileRepository fileRepository,
      FileUtils fileUtils,
      ModelMapper modelMapper) {
    this.boardRepository = boardRepository;
    this.boardMapper = boardMapper;
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
  public void boardInsert(BoardDto boardDto, List<MultipartFile> files) {
    for (MultipartFile file : files) {
      if (!file.isEmpty()) {
        log.info("파일 정보: " + file.getOriginalFilename() + " / " + file.getContentType());
      }
    }

    // // 1. 파일 저장
    List<FileDto> fileDtos = fileUtils.parseFileInfo2(files);

    // // 2. DB 저장
    // // list에 저장된 내용물이 있다면
    // if (!CollectionUtils.isEmpty(fileDtos)) {
    // List<File> files = fileDtos.stream()
    // .map((fileDto) -> modelMapper.map(fileDto, File.class))
    // .collect(Collectors.toList());

    // for (File file : files) {
    // log.info("저장할 files: " + file);
    // }
    // fileRepository.saveAll(files);
    // }

    Board board = new Board();
    board.boardDtoToBoard(boardDto);
    Board savedBoard = boardRepository.save(board);
    log.info("================> savedBoard: " + savedBoard.toString());
  }

  @Override
  public BoardDto boardDetail(Long boardIdx) {
    Board board = boardRepository.findById(boardIdx).get();
    log.info("불러온 board: " + board);

    // 조회수 증가
    board.updateHitcnt();
    BoardDto boardDto = modelMapper.map(board, BoardDto.class);
    return boardDto;
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
