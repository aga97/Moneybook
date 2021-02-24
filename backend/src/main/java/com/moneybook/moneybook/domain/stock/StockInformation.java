package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StockInformation extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "stock_information_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticker;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private Double currentPrice;

    @Builder
    public StockInformation(String ticker, String currency, Double currentPrice) {
        this.ticker = ticker;
        this.currency = currency;
        this.currentPrice = currentPrice;
    }

    public void changeCurrentPrice(Double newCurrentPrice){
        this.currentPrice = newCurrentPrice;
    }
}
