package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockTradingUpdateDto {

    private Integer year;
    private Integer month;
    private Integer day;
    private Long price;
    private Long stockQuantity;

    @Builder
    public StockTradingUpdateDto(Integer year, Integer month, Integer day, Long price, Long stockQuantity) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
