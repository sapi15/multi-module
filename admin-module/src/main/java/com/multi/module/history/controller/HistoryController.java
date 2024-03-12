package com.multi.module.history.controller;

import com.multi.module.history.entity.HistoryDto;
import com.multi.module.history.service.HistoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/admin/history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @GetMapping(value = "/test{historySeq}.do")
    public HistoryDto getHistoryInfo(HistoryDto historyDto, @PathVariable("historySeq") String historySeq){
        if(historySeq.equals("")){
            historySeq = "1";
        }
        historyDto.setHistorySeq(Integer.valueOf(historySeq));
        log.info("admin getHistoryInfo !!!");
        return historyService.findHistoryInfo(historyDto);
    }

}
