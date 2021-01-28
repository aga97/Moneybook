package com.moneybook.moneybook.dto.stocktrading;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockTradingReadRequestDto {
    private String username;
    private Integer year;
    private Integer month;
}
