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

    // 1. 파일 저장
    List<FileDto> fileDtos = fileUtils.parseFileInfoAndSave(multiFiles);

    // 2. DB 저장
    for (FileDto fileDto : fileDtos) {
      File file = new File(fileDto);
      file.setBoard(savedBoard);
      File savedFile = fileRepository.save(file);
    }
  }

  @Override
  public BoardDto boardDetail(Long boardIdx) {
    Board board = boardRepository.findById(boardIdx).get();

    // 조회수 증가
    board.updateHitcnt();
    boardRepository.save(board);
    return new BoardDto(board);
  }

  @Override
  public void boardUpdate(BoardDto boardDto) {
    Board board = boardRepository.getById(boardDto.getIdx());
    board.boardDtoToBoard(boardDto);
    boardRepository.save(board);
    return;
    // boardMapper.boardUpdate(board);
  }

  @Override
  public void boardDelete(Long boardIdx) {
    boardRepository.deleteById(boardIdx);
    // TODO : 파일 삭제 코드 추가
    return;
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
