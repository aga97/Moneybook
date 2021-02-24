package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import com.moneybook.moneybook.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@CascadeOnDelete
public class StockTrading extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "stock_trading_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_personal_id")
    private StockPersonal stockPersonal;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stockQuantity;

    @Column(nullable = false)
    private LocalDateTime tradingDate;

    @Builder
    public StockTrading(StockPersonal stockPersonal, Long price, Long stockQuantity, LocalDateTime tradingDate) {
        this.stockPersonal = stockPersonal;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.tradingDate = tradingDate;
    }

    public void changeTradingPrice(Long newPrice){
        this.price = newPrice;
    }

    public void changeStockQuantity(Long newStockQuantity){
        stockPersonal.tradeCurrentQuantity(-stockQuantity);
        stockPersonal.tradeCurrentQuantity(newStockQuantity);
        this.stockQuantity = newStockQuantity;
    }

    public void changeTradingDate(LocalDateTime newTradingDate) {
        this.tradingDate = newTradingDate;
    }

    //business logic


}
