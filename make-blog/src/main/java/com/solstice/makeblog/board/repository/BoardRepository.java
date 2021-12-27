package com.solstice.makeblog.board.repository;

import com.solstice.makeblog.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository {
    public List<Board> getBoardList();

    public Board getBoardContent(Integer bid);

    public Integer insertBoard(Board board);

    public Integer updateBoard(Board board);

    public Integer deleteBoard(Integer bid);

    public Integer updateViewCnt(Integer bid);

}