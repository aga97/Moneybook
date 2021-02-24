package com.moneybook.moneybook.domain.moneybook;

import com.moneybook.moneybook.dto.moneybook.MoneyBookReadByDateRequestDto;
import com.moneybook.moneybook.dto.moneybook.MoneyBookReadByDateResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MoneyBookQueryRepository {
    List<MoneyBookReadByDateResponseDto> findByUsernameAndDate(MoneyBookReadByDateRequestDto condition);
    List<MoneyBook> findByUsernameAndDate(String username, Integer year, Integer month);
    List<LocalDateTime> findMinMaxDateByUsername(String username);
    List<MoneyBook> findByUsernameAndTag(String username, String tag);
}
