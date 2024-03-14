package com.multi.module.board.controller;

import com.multi.module.attachFile.entity.AttachFileDto;
import com.multi.module.attachFile.service.AttachService;
import com.multi.module.board.entity.BoardDto;
import com.multi.module.board.service.BoardService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/board")
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private BoardService boardService;

    @Resource
    private AttachService attachService;




    /**
     * 게시판 목록 조회
     * @param boardDto
     * @return
     */
    @GetMapping(value = "/list.do")
    public ResponseEntity<List<BoardDto>> findBoardList(BoardDto boardDto){
        return ResponseEntity.ok()
                .body(boardService.findBoardList(boardDto));
    }

    /**
     * 게시판 상세 조회
     * @param boardDto
     * @param boardNo
     * @return
     */
    @GetMapping(value = "/{boardNo}")
    public ResponseEntity<List<BoardDto>> findBoardDetail(BoardDto boardDto, @PathVariable(value = "boardNo") Integer boardNo){
        boardDto.setBoardNo(boardNo);
        return ResponseEntity.ok()
                .body(boardService.findBoardDetail(boardDto));
    }

    /**
     * 게시판 등록
     * @param boardDto
     * @param attachFileDto
     * @param files
     * @return
     */
    @PostMapping(value = "/save.do")
    public ResponseEntity<?> saveBoard(@RequestPart(value = "data") BoardDto boardDto
                                        , AttachFileDto attachFileDto
                                        , @RequestPart(value = "uploadFiles", required = false) MultipartFile[] files){
        String result = "424";
        int cnt = boardService.boardCk(boardDto);
        try{
            if(cnt == 0){
                boolean saveCk = attachService.saveAttachFile(files, attachFileDto, boardDto);
                if(saveCk){
                    boardService.saveBoard(boardDto);
                    result = "200";
                }
            }
        }catch (Exception e){
            log.debug(e.getMessage());
        }

        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * 게시판 삭제
     * @param boardDto
     * @param boardNo
     * @return
     */
    @DeleteMapping(value = "/{boardNo}")
    public ResponseEntity<?> removeBoard(   BoardDto boardDto
                                            , @PathVariable(value = "boardNo") Integer boardNo
                                            , AttachFileDto attachFileDto){
        String result = "424";
        try{
            boardDto.setBoardNo(boardNo);
            // 관련 첨부파일도 삭제.
            attachFileDto.setBoardNo(boardDto.getBoardNo());
            boolean removeCk = attachService.removeAttachFile(attachFileDto, boardDto);
            if(removeCk){
                boardService.removeBoard(boardDto);
                result = "200";
            }
        }catch (Exception e){
            log.debug(e.getMessage());
        }

        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * 게시판 수정
     * @param boardDto
     * @param boardNo
     * @return
     */
    @PutMapping(value = "/{boardNo}")
    public ResponseEntity<?> modifyBoard(
                            //            @RequestPart(value = "data", required = false) BoardDto boardDto
                                        BoardDto boardDto
                                        , @PathVariable(value = "boardNo") Integer boardNo
                            //                                        , @RequestParam(value = "fileSaveNm") String fileSaveNm
                                        , @RequestParam(value = "fileNo", required = false) Integer fileNo
                                        , AttachFileDto attachFileDto
                                        , @RequestPart(value = "uploadFiles", required = false) MultipartFile[] files) {
        String result = "424";

        // 삭제요청된 첨부파일 데이터
        attachFileDto.setBoardNo(boardNo);
        attachFileDto.setFileNo(fileNo);
        // 삭제요청된 첨부파일 데이터

        try {
            boardDto.setBoardNo(boardNo);
            // 게시글 수정 데이터 dump
            boardDto.setBoardPNo(0);
            boardDto.setBoardTitle("제목2");
            boardDto.setBoardContents("내용2");
            boardDto.setBoardGroupNo("1");
            // 게시글 수정 데이터 dump

            boolean modifyCk = attachService.modifyAttachFile(files, attachFileDto, boardDto);
            if (modifyCk) {
                boardService.modifyBoard(boardDto);
                result = "200";
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        }

        return ResponseEntity.ok()
                .body(result);
    }


    /**
     * 파일 다운로드..
     * @param attachFileDto
     * @param fileNo
     * @param boardNo
     * @return
     */
    @GetMapping(value = "/download.do")
    public ResponseEntity<?> downloadFile( AttachFileDto attachFileDto
                                            , @RequestParam(value = "fileNo") Integer fileNo
                                            , @RequestParam(value = "boardNo") Integer boardNo){
        attachFileDto.setFileNo(fileNo);
        attachFileDto.setBoardNo(boardNo);
        AttachFileDto dto = attachService.findAttachFileDetail(attachFileDto);

        UrlResource resource;

        try{
            resource = new UrlResource("file:"+ dto.getFilePath());
        }catch (Exception e){
            log.error("the given File path is not valid");
            e.getStackTrace();
            throw new RuntimeException("the given URL path is not valid");
        }

        String originalFileName = dto.getFileOrgnNm();
        String encodedOriginalFileName = UriUtils.encode(originalFileName, StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encodedOriginalFileName + "\"";

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition)
                .body(resource);
    }




}
