package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockPersonalDto {

    private String ticker;
    private Long targetQuantity;
    private Long currentQuantity;

    @Builder
    public StockPersonalDto(String ticker, Long targetQuantity, Long currentQuantity) {
        this.ticker = ticker;
        this.targetQuantity = targetQuantity;
        this.currentQuantity = currentQuantity;
    }
}
