package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockTradingDto {

    private String ticker;
    private Long price;
    private Long stockQuantity;
    private Integer year;
    private Integer month;
    private Integer day;

    @Builder
    public StockTradingDto(String ticker, Long price, Long stockQuantity, Integer year, Integer month, Integer day) {
        this.ticker = ticker;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
