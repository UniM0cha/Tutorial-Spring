package kr.inhatc.spring.myproject.board.controller;

import kr.inhatc.spring.myproject.board.dto.BoardDto;
import kr.inhatc.spring.myproject.board.service.BoardService;
import kr.inhatc.spring.myproject.board.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {

    // 원래는 이렇게 쓰는데
//    private BoardService boardService = new BoardServiceImpl();
    @Autowired
    private BoardService boardService;

    @RequestMapping("/")
    public String hello() {
        return "index";
    }

//    @RequestMapping("/board/boardList.do")
    // 잘 안쓰이지만 이런 것도 있다.
//    public ModelAndView boardList() {
//        ModelAndView mv = new ModelAndView("board/boardList");
//
//        // 서비스에서 리스트를 받아온 다음
//        List<BoardDto> list = boardService.boardList();
//
//        // list라는 이름으로 받아온 list를 보내준다.
//        mv.addObject("list", list);
//        return mv;
//    }
    @RequestMapping("/board/boardList.do")
    public String boardList(Model model) {
        // 서비스에서 리스트를 받아온 다음
        List<BoardDto> list = boardService.boardList();
        System.out.println("list.size() = " + list.size());

        // list라는 이름으로 받아온 list를 보내준다.
        model.addAttribute("list", list);

        // 리턴값은 뷰 경로
        return "board/boardList";
    }
}
