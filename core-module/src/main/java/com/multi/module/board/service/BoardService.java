package com.multi.module.board.service;

import com.multi.module.board.entity.BoardDto;

import java.util.List;

public interface BoardService {

    List<BoardDto> findBoardList(BoardDto boardDto);

    List<BoardDto> findBoardDetail(BoardDto boardDto);

    int saveBoard(BoardDto boardDto);

    int modifyBoard(BoardDto boardDto);

    int removeBoard(BoardDto boardDto);

    int boardCk(BoardDto boardDto);
}
