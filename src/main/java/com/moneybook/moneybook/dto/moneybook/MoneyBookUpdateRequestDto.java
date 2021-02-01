package com.moneybook.moneybook.dto.moneybook;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyBookUpdateRequestDto {

    private Long id;
    private String context;
    private Long amount;
    private String tag;
    private Integer year;
    private Integer month;
    private Integer day;

    @Builder
    public MoneyBookUpdateRequestDto(Long id, String context, Long amount, String tag, Integer year, Integer month, Integer day) {
        this.id = id;
        this.context = context;
        this.amount = amount;
        this.tag = tag;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
