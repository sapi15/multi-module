package com.multi.module.history.controller;

import com.multi.module.history.entity.HistoryDto;
import com.multi.module.history.service.HistoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/client/history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @GetMapping(value = "/test.do")
    public HistoryDto getHistoryInfo(HistoryDto historyDto){
        historyDto.setHistorySeq(2);
        log.info("client getHistoryInfo !!!");
        return historyService.findHistoryInfo(historyDto);
    }
}
