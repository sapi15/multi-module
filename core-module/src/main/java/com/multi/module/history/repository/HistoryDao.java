package com.multi.module.history.repository;

import com.multi.module.history.entity.HistoryDto;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDao {

    HistoryDto selectHistoryInfo(HistoryDto historyDto);


}
