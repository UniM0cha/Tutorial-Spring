package com.example.javajava.board.repository;

import com.example.javajava.board.entity.Board;
import com.example.javajava.board.entity.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
  public File findByIdxAndBoard(Long idx, Board board);
}