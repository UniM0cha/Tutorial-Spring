package com.solstice.makeblog.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
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
    private LocalDateTime reg_dt;
    private LocalDateTime edit_dt;
}
