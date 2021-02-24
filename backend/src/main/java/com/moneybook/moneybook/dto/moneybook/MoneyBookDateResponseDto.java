package com.moneybook.moneybook.dto.moneybook;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyBookDateResponseDto {
    private Integer minYear;
    private Integer minMonth;
    private Integer maxYear;
    private Integer maxMonth;

    @Builder
    public MoneyBookDateResponseDto(Integer minYear, Integer minMonth, Integer maxYear, Integer maxMonth) {
        this.minYear = minYear;
        this.minMonth = minMonth;
        this.maxYear = maxYear;
        this.maxMonth = maxMonth;
    }
}
