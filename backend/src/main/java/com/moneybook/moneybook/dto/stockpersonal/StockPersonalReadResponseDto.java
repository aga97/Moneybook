package com.moneybook.moneybook.dto.stockpersonal;

import com.moneybook.moneybook.domain.stock.StockPersonal;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockPersonalReadResponseDto {
    private Long id;
    private String ticker;
    private Long targetQuantity;
    private Long currentQuantity;

    public StockPersonalReadResponseDto(StockPersonal entity) {
        this.id = entity.getId();
        this.ticker = entity.getStockInformation().getTicker();
        this.targetQuantity = entity.getTargetQuantity();
        this.currentQuantity = entity.getCurrentQuantity();
    }
}
