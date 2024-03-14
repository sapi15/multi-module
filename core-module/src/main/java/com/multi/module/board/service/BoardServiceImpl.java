package com.multi.module.board.service;

import com.multi.module.board.entity.BoardDto;
import com.multi.module.board.repository.BoardDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Resource
    private BoardDao boardDao;


    @Override
    public List<BoardDto> findBoardList(BoardDto boardDto) {
        return boardDao.selectBoardList(boardDto);
    }

    @Override
    public List<BoardDto> findBoardDetail(BoardDto boardDto) {
        return boardDao.selectBoardDetail(boardDto);
    }

    @Override
    public int saveBoard(BoardDto boardDto) {
        return boardDao.insertBoard(boardDto);
    }

    @Override
    public int modifyBoard(BoardDto boardDto) {
        return boardDao.updateBoard(boardDto);
    }

    @Override
    public int removeBoard(BoardDto boardDto) {
        return boardDao.deleteBoard(boardDto);
    }

    @Override
    public int boardCk(BoardDto boardDto) {
        return boardDao.boardCk(boardDto);
    }
}
