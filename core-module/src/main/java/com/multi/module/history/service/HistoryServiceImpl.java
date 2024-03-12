package com.multi.module.history.service;

import com.multi.module.history.entity.HistoryDto;
import com.multi.module.history.repository.HistoryDao;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService{

    @Resource
    private HistoryDao historyDao;

//    private final HistoryDao historyDao;


    @Override
    public HistoryDto findHistoryInfo(HistoryDto historyDto) {
        return historyDao.selectHistoryInfo(historyDto);
    }
}
