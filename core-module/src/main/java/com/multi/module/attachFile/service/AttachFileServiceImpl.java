package com.multi.module.attachFile.service;

import com.multi.module.attachFile.entity.AttachFileDto;
import com.multi.module.attachFile.repository.AttachFileDao;
import com.multi.module.board.entity.BoardDto;
import com.multi.module.common.utils.Validations;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class AttachFileServiceImpl implements AttachService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AttachFileDao attachFileDao;

    @Value("${part.upload.path}")
    private String uploadPath;


    @Override
    public AttachFileDto findAttachFileDetail(AttachFileDto attachFileDto) {
        return attachFileDao.selectAttachFileDetail(attachFileDto);
    }

    @Override
    public boolean saveAttachFile(MultipartFile[] files, AttachFileDto attachFileDto, BoardDto boardDto) {
        boolean flag = false;
        if(Validations.isNotNull(files)){
            boolean result = uploadFile(files, attachFileDto, boardDto);
            if(result) {
                flag = true;
            }else{
                logger.info("첨부파일이 저장 오류.");
            }
        }else{
            logger.info("첨부파일이 없는 게시글.");
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean modifyAttachFile(MultipartFile[] files, AttachFileDto attachFileDto, BoardDto boardDto) {
        boolean flag = false;
        if(Validations.isNotNull(files)){
            boolean result = modifyFile(files, attachFileDto, boardDto);
            if(result){
                attachFileDao.updateAttachFile(attachFileDto);
                flag = true;
            }
        }else{
            logger.info("첨부파일이 없는 게시글");
            flag = true;
        }

        return flag;
    }

    @Override
    public boolean removeAttachFile(AttachFileDto attachFileDto, BoardDto boardDto) {
        boolean flag = false;
        String fileFolderPath = uploadPath + File.separator + boardDto.getBoardNo() + File.separator;
        boolean result = removeFile(fileFolderPath);
        if(result){
            attachFileDao.deleteAttachFile(attachFileDto);
            flag = true;
        }

        return flag;
    }



    /**
     * 물리적 파일 업로드.
     * @param files
     * @return
     * @throws Exception
     */
    public boolean uploadFile(MultipartFile[] files, AttachFileDto attachFileDto, BoardDto boardDto){
        boolean flag = false;

        if(Validations.isNotNull(files)){
            for(MultipartFile file : files){
                String boardNo = boardDto.getBoardNo().toString();
//            if(uploadFile.getContentType().startsWith("image") == false){
//                logger.warn("this file is not image type");
//                return false;
//            }

//            String originalName = uploadFile.getOriginalFilename();
//            String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
//            String folderPath = makeFolder();                 // 날짜 폴더 생성
//            String uuid = UUID.randomUUID().toString();         // UUID
//            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;    // 저장파일 구분

                String originalName = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String filePath = uploadPath + File.separator + boardNo + File.separator;
                String saveName = filePath + uuid + "_" + originalName;    // 저장파일 구분

                File fileFolderPath = new File(filePath);       // 폴더 생성.

                if(!fileFolderPath.exists()){
                    fileFolderPath.mkdirs();              // 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
                }

                Path savePath = Paths.get(saveName);

                try{
                    file.transferTo(savePath);    // 파일 업로드.
                    flag = true;
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(flag){
                    attachFileDto.setBoardNo(Integer.valueOf(boardNo));
                    attachFileDto.setFileOrgnNm(originalName);
                    attachFileDto.setFileSaveNm(uuid);
                    attachFileDto.setFileSize(file.getSize());
                    attachFileDto.setFilePath(filePath);
                    attachFileDto.setFileType(file.getContentType());

                    attachFileDao.insertAttachFile(attachFileDto);
                }

            }
        }

        return flag;
    }

    /**
     * localDate 형식으로 path 생성.
     * @return
     */
    private String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));     // LocalDate를 문자열로 포멧.
        String folderPath = str.replace("/", File.separator);                           // 폴더 경로.

        File uploadPathFolder = new File(uploadPath, folderPath);       // 폴더 생성.

        if(!uploadPathFolder.exists()){
            uploadPathFolder.mkdirs();              // 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
        }

        return folderPath;
    }

    /**
     * 물리적 파일 삭제.
     * @param fileFolderPath
     * @return
     */
    public boolean removeFile(String fileFolderPath){
        boolean flag = false;

        System.out.println("fileFolderPath->"+fileFolderPath);

        File file = new File(fileFolderPath);

        if(file.exists()){
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++){
                    if(files[i].delete()){                                  // 폴더 안에 파일이 있으면, 파일 먼저 삭제.
                        logger.info(files[i].getName()+" 삭제 성공");
                    }
                }
            }

            file.delete();                          // 폴더 삭제.
            flag = true;
        }else{
            logger.info("첨부 파일이 없는 게시글.");
            flag = true;
        }
        return flag;
    }


    /**
     * 파일 수정.
     * @param files
     * @param attachFileDto
     * @param boardDto
     * @return
     */
    public boolean modifyFile(MultipartFile[] files, AttachFileDto attachFileDto, BoardDto boardDto){
        /**
         * 삭제할 파일 데이터와 신규 저장 파일 데이터 2개가 있어야 한다?
         * 저장 요청이 들어오면, 해당 파일을 삭제하고, 신규 파일은 saveFile 처럼. https://non-stop.tistory.com/542
         */
        boolean flag = false;
        if(Validations.isNotNull(files)){
            // 삭제 요청 들어온 파일 삭제(물리적 및 table 정보)
            AttachFileDto dto = attachFileDao.selectAttachFileDetail(attachFileDto);
            String fileName = dto.getFileSaveNm() + "_" + dto.getFileOrgnNm();
            String fileFolderPath = uploadPath + File.separator + dto.getBoardNo() + File.separator + fileName;

            File file = new File(fileFolderPath);

            if(file.exists()){
                file.delete();
                attachFileDao.deleteAttachFile2(attachFileDto);
            }
            // 삭제 요청 들어온 파일 삭제(물리적 및 table 정보)

            // 새로 저장요청 들어온 파일 저장.
            for(MultipartFile uploadFile : files){
                String boardNo = boardDto.getBoardNo().toString();
                String originalName = uploadFile.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String filePath = uploadPath + File.separator + boardNo + File.separator;
                String saveName = filePath + uuid + "_" + originalName;    // 저장파일 구분

                File fileFolderPath2 = new File(filePath);       // 폴더 생성.

                if(!fileFolderPath2.exists()){
                    fileFolderPath2.mkdirs();              // 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
                }

                Path savePath = Paths.get(saveName);

                try{
                    uploadFile.transferTo(savePath);    // 파일 업로드.
                    flag = true;
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(flag){
                    attachFileDto.setBoardNo(Integer.valueOf(boardNo));
                    attachFileDto.setFileOrgnNm(originalName);
                    attachFileDto.setFileSaveNm(uuid);
                    attachFileDto.setFileSize(uploadFile.getSize());
                    attachFileDto.setFilePath(filePath);
                    attachFileDto.setFileType(uploadFile.getContentType());

                    attachFileDao.insertAttachFile(attachFileDto);
                }

            }
            // 새로 저장요청 들어온 파일 저장.


            flag = true;
        }

        return flag;
    }



}
