package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockPersonalUpdateDto {

    private Long targetQuantity;
    private Long currentQuantityWeight;

    @Builder
    public StockPersonalUpdateDto(Long targetQuantity, Long currentQuantityWeight) {
        this.targetQuantity = targetQuantity;
        this.currentQuantityWeight = currentQuantityWeight;
    }
}
