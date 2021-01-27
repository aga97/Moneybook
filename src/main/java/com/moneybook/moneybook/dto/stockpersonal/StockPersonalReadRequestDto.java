package com.moneybook.moneybook.dto.stockpersonal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockPersonalReadRequestDto {

    private String username;

    @Builder
    public StockPersonalReadRequestDto(String username) {
        this.username = username;
    }
}
