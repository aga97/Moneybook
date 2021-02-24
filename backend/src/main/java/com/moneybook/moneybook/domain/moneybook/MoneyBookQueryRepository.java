package com.moneybook.moneybook.domain.moneybook;

import com.moneybook.moneybook.dto.moneybook.MoneyBookReadRequestDto;
import com.moneybook.moneybook.dto.moneybook.MoneyBookReadResponseDto;
import com.querydsl.core.Tuple;

import java.time.LocalDateTime;
import java.util.List;

public interface MoneyBookQueryRepository {
    List<MoneyBookReadResponseDto> findByUsernameAndDate(MoneyBookReadRequestDto condition);
    List<MoneyBook> findByUsernameAndDate(String username, Integer year, Integer month);
    List<LocalDateTime> findMinMaxDateByUsername(String username);
}
