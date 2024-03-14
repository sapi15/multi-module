package com.multi.module.board.entity;

import lombok.Data;

@Data
public class BoardDto {

    private Integer boardNo;

    private Integer boardPNo;

    private String boardTitle;

    private String boardContents;

    private String boardGroupNo;

}
