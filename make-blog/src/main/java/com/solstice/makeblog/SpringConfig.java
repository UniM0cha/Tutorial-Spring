package com.solstice.makeblog;

import com.solstice.makeblog.board.repository.BoardRepository;
import com.solstice.makeblog.board.repository.BoardRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public BoardRepository boardRepository() {
        return new BoardRepositoryJpa(em);
    }
}
