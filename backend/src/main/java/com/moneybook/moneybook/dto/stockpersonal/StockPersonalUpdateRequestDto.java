package com.moneybook.moneybook.dto.stockpersonal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockPersonalUpdateRequestDto {

    private Long id;
    private Long targetQuantity;
    private Long currentQuantity;

    @Builder
    public StockPersonalUpdateRequestDto(Long id, Long targetQuantity, Long currentQuantity) {
        this.id = id;
        this.targetQuantity = targetQuantity;
        this.currentQuantity = currentQuantity;
    }
}
