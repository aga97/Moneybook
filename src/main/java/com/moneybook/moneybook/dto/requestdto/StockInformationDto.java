package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockInformationDto {

    private String ticker;
    private String currency;
    private Double currentPrice;

    @Builder
    public StockInformationDto(String ticker, String currency, Double currentPrice) {
        this.ticker = ticker;
        this.currency = currency;
        this.currentPrice = currentPrice;
    }
}
