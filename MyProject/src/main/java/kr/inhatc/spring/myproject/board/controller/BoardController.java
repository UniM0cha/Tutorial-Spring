package kr.inhatc.spring.myproject.board.controller;

import kr.inhatc.spring.myproject.board.dto.BoardDto;
import kr.inhatc.spring.myproject.board.dto.FileDto;
import kr.inhatc.spring.myproject.board.service.BoardService;
import kr.inhatc.spring.myproject.board.service.BoardServiceImpl;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

@Controller
public class BoardController {

    // 어노테이션 중에 sl4j 라는게 있다.
    // private Logger log = LoggerFactory.getLogger(BoardController.class);
    private Logger log = LoggerFactory.getLogger(this.getClass());

    // 원래는 이렇게 쓰는데
    // private BoardService boardService = new BoardServiceImpl();
    // Autowired를 붙히면 빈으로 둥록된 클래스를 불러온다.
    @Autowired
    private BoardService boardService;

    // @RequestMapping("/")
    // public String hello() {
    // return "index";
    // }

    // @RequestMapping("/board/boardList.do")
    // 잘 안쓰이지만 이런 것도 있다.
    // public ModelAndView boardList() {
    // ModelAndView mv = new ModelAndView("board/boardList");
    //
    // // 서비스에서 리스트를 받아온 다음
    // List<BoardDto> list = boardService.boardList();
    //
    // // list라는 이름으로 받아온 list를 보내준다.
    // mv.addObject("list", list);
    // return mv;
    // }

    // RESTFUL 프로그래밍에서는 .do를 쓰지 않는다
    @RequestMapping("/board/boardList")
    public String boardList(Model model) {
        // 서비스에서 리스트를 받아온 다음
        List<BoardDto> list = boardService.boardList();
        log.debug("Access to boardList ==============> " + list.size());
        // System.out.println("list.size() = " + list.size());

        // list라는 이름으로 받아온 list를 보내준다.
        model.addAttribute("list", list);

        // 리턴값은 뷰 경로
        return "board/boardList";
    }

    @RequestMapping("/board/boardWrite")
    public String boardWrite(Model model) {
        return "board/boardWrite";
    }

    @RequestMapping("/board/boardInsert")
    // model 은 컨트롤러에서 뷰로 넘겨줄 때 쓰는 것이므로 필요 없다.
    // BoardDto를 정의해놓으면 form을 통해서 받은 내용을 board에 자동으로 삽입하게 된다.
    // 파일 삽입한 것을 받아오기 위해 Multipart--- 어쩌구를 넣어준다.
    public String boardInsert(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) {
        boardService.boardInsert(board, multipartHttpServletRequest);
        return "redirect:/board/boardList";
    }

    @RequestMapping("/board/boardDetail")
    // model 은 컨트롤러에서 뷰로 넘겨줄 때 쓰는 것이므로 필요 없다.
    // BoardDto를 정의해놓으면 form을 통해서 받은 내용을 board에 자동으로 삽입하게 된다.
    public String boardDetail(@RequestParam int boardIdx, Model model) {

        // 보드 인덱스를 통하여 보드 객체를 받아온다.
        BoardDto board = boardService.boardDetail(boardIdx);
        System.out.println("boardIdx = " + boardIdx);
        System.out.println("board = " + board);

        // 모델에 board란 속성에 board 객체 등록
        model.addAttribute("board", board);
        return "/board/boardDetail";
    }

    @RequestMapping("/board/boardUpdate")
    public String boardUpdate(BoardDto board) {
        // System.out.println("board.getBoardIdx() = " + board.getBoardIdx());
        // System.out.println("board.getTitle() = " + board.getTitle());

        boardService.boardUpdate(board);
        return "redirect:/board/boardList";
    }

    @RequestMapping("/board/boardDelete")
    // @PathVariable 이란 것도 있다. 찾아보자
    public String boardDelete(@RequestParam int boardIdx) {
        boardService.boardDelete(boardIdx);
        return "redirect:/board/boardList";
    }

    @RequestMapping("/board/downloadBoardFile")
    public void downloadBoardFile(
            @RequestParam("idx") int idx,
            @RequestParam("boardIdx") int boardIdx,
            HttpServletResponse response) throws IOException {

        FileDto boardFile = boardService.selectFileInfo(idx, boardIdx);
        if (!ObjectUtils.isEmpty(boardFile)) {
            String fileName = boardFile.getOriginalFileName();
            byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));

            // response 헤더 설정...
            response.setContentType("application/octet-stream");
            response.setContentLength(files.length);
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            response.setHeader("Content-Transfer-Encoding", "binary");

            response.getOutputStream().write(files);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }

}
