package com.solstice.makeblog.board.repository;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Board {

    @Id
    @GeneratedValue
    private Integer bid;
    private String cate_cd;
    private String title;
    private String content;
    private String tag;
    private Integer view_cnt;
    private String reg_id;
    private String reg_dt;
    private String edit_dt;
}
