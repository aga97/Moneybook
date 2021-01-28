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
}
