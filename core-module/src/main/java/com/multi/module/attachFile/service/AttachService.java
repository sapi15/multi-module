package com.multi.module.attachFile.service;

import com.multi.module.attachFile.entity.AttachFileDto;
import com.multi.module.board.entity.BoardDto;
import org.springframework.web.multipart.MultipartFile;

public interface AttachService {

    AttachFileDto findAttachFileDetail(AttachFileDto attachFileDto);

    boolean saveAttachFile(MultipartFile[] files, AttachFileDto attachFileDto, BoardDto boardDto);

    boolean modifyAttachFile(MultipartFile[] files, AttachFileDto attachFileDto, BoardDto boardDto);

    boolean removeAttachFile(AttachFileDto attachFileDto, BoardDto boardDto);
}
