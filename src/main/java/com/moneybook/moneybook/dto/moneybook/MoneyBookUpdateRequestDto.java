package com.moneybook.moneybook.dto.moneybook;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyBookUpdateRequestDto {
    private Long id;
    private Integer year;
    private Integer month;
    private Integer day;
    private String context;
    private Long amount;
    private String tag;

    @Builder
    public MoneyBookUpdateRequestDto(Long id, Integer year, Integer month, Integer day, String context, Long amount, String tag) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.context = context;
        this.amount = amount;
        this.tag = tag;
    }
}
