package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class StockTrading extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "stock_trading_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Column(nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_information_id")
    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stockQuantity;

    @Column(nullable = false)
    private LocalDateTime tradingDate;

    @Builder
    public StockTrading(String username, String ticker, Long price, Long stockQuantity, LocalDateTime tradingDate) {
        this.username = username;
        this.ticker = ticker;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.tradingDate = tradingDate;
    }


    public void changeStockQuantity(Long newStockQuantity){
        this.stockQuantity = newStockQuantity;
    }

    public void changeTradingDate(LocalDateTime newTradingDate) {
        this.tradingDate = newTradingDate;
    }

    //business logic


}
