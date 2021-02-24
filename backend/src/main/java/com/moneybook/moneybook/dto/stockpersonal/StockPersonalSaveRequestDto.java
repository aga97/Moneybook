package com.moneybook.moneybook.dto.stockpersonal;

import com.moneybook.moneybook.domain.stock.StockPersonal;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockPersonalSaveRequestDto {
    private String username;
    private String ticker;
    private Long targetQuantity;
    private Long currentQuantity;

    @Builder
    public StockPersonalSaveRequestDto(String username, String ticker, Long targetQuantity, Long currentQuantity) {
        this.username = username;
        this.ticker = ticker;
        this.targetQuantity = targetQuantity;
        this.currentQuantity = currentQuantity;
    }
}
