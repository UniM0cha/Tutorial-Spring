package com.solstice.makeblog;

import com.solstice.makeblog.board.repository.Board;
import com.solstice.makeblog.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MysqlConnectionTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void 데이터베이스_연결() {
        List<Board> all = boardRepository.findAll();
        System.out.println("all = " + all);
    }
}
