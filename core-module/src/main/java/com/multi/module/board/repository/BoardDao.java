package com.multi.module.board.repository;

import com.multi.module.board.entity.BoardDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDao {

    List<BoardDto> selectBoardList(BoardDto boardDto);

    List<BoardDto> selectBoardDetail(BoardDto boardDto);

    int insertBoard(BoardDto boardDto);

    int updateBoard(BoardDto boardDto);

    int deleteBoard(BoardDto boardDto);

    int boardCk(BoardDto boardDto);



}
