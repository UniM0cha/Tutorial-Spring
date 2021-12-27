package com.solstice.makeblog;

import com.solstice.makeblog.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MakeBlogApplication {


    public static void main(String[] args) {

        SpringApplication.run(MakeBlogApplication.class, args);
    }

}
