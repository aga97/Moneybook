package com.moneybook.moneybook.dto.stocktrading;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockTradingUpdateRequestDto {
    private Long id;
    private Integer year;
    private Integer month;
    private Integer day;
    private Long price;
    private Long stockQuantity;

    @Builder
    public StockTradingUpdateRequestDto(Long id, Integer year, Integer month, Integer day, Long price, Long stockQuantity) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
