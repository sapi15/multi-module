package com.multi.module.attachFile.entity;

import lombok.Data;

@Data
public class AttachFileDto {

    private Integer fileNo;

    private Integer boardNo;

    private String fileOrgnNm;

    private String fileSaveNm;

    private Long fileSize;

    private String filePath;

    private String fileType;
}
