package com.moneybook.moneybook.dto.stocktrading;

import com.moneybook.moneybook.domain.stock.StockTrading;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockTradingSaveRequestDto {
    private String username;
    private String ticker;
    private Long price;
    private Long stockQuantity;
    private Integer year;
    private Integer month;
    private Integer day;

    @Builder
    public StockTradingSaveRequestDto(String username, String ticker, Long price, Long stockQuantity, Integer year, Integer month, Integer day) {
        this.username = username;
        this.ticker = ticker;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public StockTrading toEntity() {
        return StockTrading.builder()
                .username(username)
                .ticker(ticker)
                .price(price)
                .stockQuantity(stockQuantity)
                .tradingDate(LocalDateTime.of(year, month, day, 0, 0))
                .build();
    }
}
