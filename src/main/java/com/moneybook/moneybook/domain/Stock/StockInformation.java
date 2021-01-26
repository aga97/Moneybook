package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class StockInformation extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "stock_information_id")
    private Long id;

    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private Long currentPrice;

    @Builder
    public StockInformation(String ticker, Long currentPrice) {
        this.ticker = ticker;
        this.currentPrice = currentPrice;
    }
}
