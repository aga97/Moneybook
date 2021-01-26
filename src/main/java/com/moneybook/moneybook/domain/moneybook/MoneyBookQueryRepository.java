package com.moneybook.moneybook.domain.moneybook;

import com.moneybook.moneybook.dto.moneybook.MoneyBookReadRequestDto;
import com.moneybook.moneybook.dto.moneybook.MoneyBookReadResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface MoneyBookQueryRepository {
    List<MoneyBookReadResponseDto> findByUsernameAndDate(MoneyBookReadRequestDto condition);
}
