package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyBookDto {

    private Integer year;
    private Integer month;
    private Integer day;
    private String context;
    private Long amount;
    private String tag;

    @Builder
    public MoneyBookDto(Integer year, Integer month, Integer day, String context, Long amount, String tag) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.context = context;
        this.amount = amount;
        this.tag = tag;
    }
}
