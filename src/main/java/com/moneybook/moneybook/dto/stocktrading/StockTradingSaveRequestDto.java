package com.moneybook.moneybook.dto.stocktrading;

import com.moneybook.moneybook.domain.stock.StockTrading;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class StockTradingSaveRequestDto {
    private String username;
    private String ticker;
    private Long price;
    private Long stockQuantity;
    private Integer year;
    private Integer month;
    private Integer day;
}
