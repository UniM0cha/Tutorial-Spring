package kr.inhatc.spring.myproject.board.service;

import kr.inhatc.spring.myproject.board.dto.BoardDto;
import kr.inhatc.spring.myproject.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;
    // 나중에 클래스 주입으로 바꿔보자

    @Override
    public List<BoardDto> boardList() {
        return boardMapper.boardList();
    }

    @Override
    public void boardInsert(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) {
        boardMapper.boardInsert(board);

        // 임시
        if(!ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            Iterator<String> iter = multipartHttpServletRequest.getFileNames();

            while (iter.hasNext()) {
                String name = iter.next();
                List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
                for (MultipartFile multipartFile : list) {
                    System.out.println("=====================>file name = " + multipartFile.getOriginalFilename());
                    System.out.println("=====================>file size = " + multipartFile.getSize());
                    System.out.println("=====================>file type = " + multipartFile.getContentType());
                }
            }
        }
    }

    @Override
    public BoardDto boardDetail(int boardIdx) {
        boardMapper.updateHit(boardIdx);
        BoardDto board = boardMapper.boardDetail(boardIdx);
        return board;
    }

    @Override
    public void boardUpdate(BoardDto board) {
        boardMapper.boardUpdate(board);
    }

    @Override
    public void boardDelete(int boardIdx) {
        boardMapper.boardDelete(boardIdx);
    }
}
