package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockPersonalUpdateDto {

    private Long targetQuantity;
    private Long currentQuantity;

    @Builder
    public StockPersonalUpdateDto(Long targetQuantity, Long currentQuantity) {
        this.targetQuantity = targetQuantity;
        this.currentQuantity = currentQuantity;
    }
}
