package kr.inhatc.spring.board.controller;

import kr.inhatc.spring.board.dto.BoardDto;
import kr.inhatc.spring.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BoardContorller {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/board/boardList.do")
    public String boardList(Model model) {
        List<BoardDto> list = boardService.boardList();
        System.out.println("list.size() = " + list.size());
        model.addAttribute("list", list);
        return "board/boardList";
    }
}
