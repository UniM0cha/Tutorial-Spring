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
import org.springframework.web.multipart.MultipartFile;

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
  public BoardServiceJpaImpl(
      BoardRepository boardRepository,
      FileRepository fileRepository,
      FileUtils fileUtils,
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
  public void boardInsert(BoardDto boardDto, List<MultipartFile> multiFiles) {

    Board board = new Board();
    board.boardDtoToBoard(boardDto);
    Board savedBoard = boardRepository.save(board);
    log.info("데이터베이스 저장된 게시물 정보: " + savedBoard.toString());

    // 1. 파일 저장
    List<FileDto> fileDtos = fileUtils.parseFileInfoAndSave(multiFiles);

    // 2. DB 저장
    for (FileDto fileDto : fileDtos) {
      File file = new File(fileDto);
      file.setBoard(savedBoard);
      File savedFile = fileRepository.save(file);
      log.info("데이터베이스 저장된 파일 정보: " + savedFile.toString());
    }
  }

  @Override
  public BoardDto boardDetail(Long boardIdx) {
    Board board = boardRepository.findById(boardIdx).get();
    log.info("불러온 board: " + board.toString());

    // 조회수 증가
    // board.updateHitcnt();
    // BoardDto boardDto = modelMapper.map(board, BoardDto.class);
    // return boardDto;
    return new BoardDto();
  }

  @Override
  public void boardUpdate(BoardDto boardDto) {
    Board board = boardRepository.getById(boardDto.getIdx());
    board.boardDtoToBoard(boardDto);
    return;
    // boardMapper.boardUpdate(board);
  }

  @Override
  public void boardDelete(Long boardIdx) {
    boardRepository.deleteById(boardIdx);
    // boardMapper.boardDelete(boardIdx);
  }

  @Override
  public FileDto selectFileInfo(Long idx, Long boardIdx) {
    Board board = boardRepository.findById(boardIdx).get();
    File file = fileRepository.findByIdxAndBoard(idx, board);

    FileDto fileDto = new FileDto(file);

    // FileDto boardFile = boardMapper.selectFileInfo(idx, boardIdx);
    return fileDto;
  }
}
