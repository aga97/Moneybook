package com.moneybook.moneybook.dto.stocktrading;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockTradingUpdateRequestDto {

    private Long id;
    private Long price;
    private Long stockQuantity;
    private Integer year;
    private Integer month;
    private Integer day;

    @Builder
    public StockTradingUpdateRequestDto(Long id, Long price, Long stockQuantity, Integer year, Integer month, Integer day) {
        this.id = id;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
