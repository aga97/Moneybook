package com.moneybook.moneybook.dto.stockinformation;

import com.moneybook.moneybook.domain.stock.StockInformation;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockInformationSaveRequestDto {

    private String ticker;
    private String currency;
    private Double currentPrice;

    @Builder
    public StockInformationSaveRequestDto(String ticker, String currency , Double currentPrice) {
        this.ticker = ticker;
        this.currency = currency;
        this.currentPrice = currentPrice;
    }
}
