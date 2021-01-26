package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class StockTrading extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "stock_trading_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private long stockQuantity;

    @Builder
    public StockTrading(String username, String ticker, Long price, long stockQuantity) {
        this.username = username;
        this.ticker = ticker;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
