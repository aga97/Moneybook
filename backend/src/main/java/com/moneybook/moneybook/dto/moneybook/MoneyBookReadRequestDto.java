package com.moneybook.moneybook.dto.moneybook;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyBookReadRequestDto {
    private String username;
    private Integer year;
    private Integer month;

    @Builder
    public MoneyBookReadRequestDto(String username, Integer year, Integer month) {
        this.username = username;
        this.year = year;
        this.month = month;
    }
}
