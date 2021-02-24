package com.moneybook.moneybook.dto.stocktrading;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockTradingReadByTickerRequestDto {

    private String username;
    private String ticker;

    @Builder
    public StockTradingReadByTickerRequestDto(String username, String ticker) {
        this.username = username;
        this.ticker = ticker;
    }
}
