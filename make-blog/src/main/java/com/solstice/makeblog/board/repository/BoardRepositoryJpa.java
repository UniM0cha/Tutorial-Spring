package com.solstice.makeblog.board.repository;

import com.solstice.makeblog.board.domain.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public class BoardRepositoryJpa implements BoardRepository {

    private final EntityManager em;

    public BoardRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Board> getBoardList() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    @Override
    public Board getBoardContent(Integer bid) {
        return em.find(Board.class, bid);
    }

    @Override
    public Integer insertBoard(Board board) {
        em.persist(board);
        return board.getBid();
    }

    @Override
    public Integer updateBoard(Board board) {
        Board findBoard = em.find(Board.class, board.getBid());
        findBoard.setContent(board.getContent());
        findBoard.setCate_cd(board.getCate_cd());
        findBoard.setEdit_dt(board.getEdit_dt());
        findBoard.setReg_dt(board.getReg_dt());
        findBoard.setTag(board.getTag());
        findBoard.setTitle(board.getTitle());
        findBoard.setView_cnt(board.getView_cnt());
        findBoard.setReg_id(board.getReg_id());
        return null;
    }

    @Override
    public Integer deleteBoard(Integer bid) {
        return null;
    }

    @Override
    public Integer updateViewCnt(Integer bid) {
        return null;
    }
}
