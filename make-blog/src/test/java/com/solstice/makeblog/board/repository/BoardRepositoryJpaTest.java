package com.solstice.makeblog.board.repository;

import com.solstice.makeblog.board.domain.Board;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
class BoardRepositoryJpaTest {
    private static final Logger logger = LoggerFactory.getLogger(BoardRepositoryJpaTest.class);

    @Autowired
    BoardRepository boardRepository;

    @Test
    void getBoardList() {
        // given

        // when
        List<Board> boardList = boardRepository.getBoardList();


        // then
        System.out.println("boardList = " + boardList);
    }

    @Test
    void testGetBoardList() {
    }

    @Test
    void getBoardContent() {
    }

    @Test
    void insertBoard() {
    }

    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }

    @Test
    void updateViewCnt() {
    }
}