package com.moneybook.moneybook.dto.stocktrading;

import com.moneybook.moneybook.domain.stock.StockTrading;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockTradingReadResponseDto {

    private Long id;
    private String ticker;
    private Long price;
    private Long stockQuantity;
    private Integer day;

    public StockTradingReadResponseDto(StockTrading entity) {
        this.id = entity.getId();
        this.ticker = entity.getTicker();
        this.price = entity.getPrice();
        this.stockQuantity = entity.getStockQuantity();
        this.day = entity.getTradingDate().getDayOfMonth();
    }
}
