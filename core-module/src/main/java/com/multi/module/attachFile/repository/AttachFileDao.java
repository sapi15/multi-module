package com.multi.module.attachFile.repository;

import com.multi.module.attachFile.entity.AttachFileDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachFileDao {

    AttachFileDto selectAttachFileDetail(AttachFileDto attachFileDto);

    int insertAttachFile(AttachFileDto attachFileDto);

    int updateAttachFile(AttachFileDto attachFileDto);

    int deleteAttachFile(AttachFileDto attachFileDto);

    int deleteAttachFile2(AttachFileDto attachFileDto);

}
