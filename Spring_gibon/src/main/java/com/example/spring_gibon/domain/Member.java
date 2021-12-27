package com.example.spring_gibon.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id;
    private String name;
    private String grade;

    public Member(Long id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
}
