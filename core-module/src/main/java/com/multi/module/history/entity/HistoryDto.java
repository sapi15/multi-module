package com.multi.module.history.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Table(name = "TB_HISTORY")
public class HistoryDto {

    @Id
    @Column(name = "HISTORY_SEQ")
    private Integer historySeq;

    @Column(name = "REGIST_DATETIME")
    private String registDateTime;

    @Column(name = "HISTORY_CONTENT")
    private String historyContent;
}
