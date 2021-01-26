package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import com.moneybook.moneybook.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StockTrading extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "stock_trading_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_information_id")
    private StockInformation stockInformation;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stockQuantity;

    @Column(nullable = false)
    private LocalDateTime tradingDate;

    @Builder
    public StockTrading(Member member, StockInformation stockInformation, Long price, Long stockQuantity, LocalDateTime tradingDate) {
        this.member = member;
        this.stockInformation = stockInformation;
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
