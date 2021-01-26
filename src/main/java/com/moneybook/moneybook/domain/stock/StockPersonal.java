package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class StockPersonal extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_information_id")
    private String ticker;

    private Long targetQuantity;
    private Long currentQuantity;

    @Builder
    public StockPersonal(String username, String ticker, Long targetQuantity, Long currentQuantity) {
        this.username = username;
        this.ticker = ticker;
        this.targetQuantity = targetQuantity;
        this.currentQuantity = currentQuantity;
    }

    public void changeTargetQuantity(Long newTargetQuantity) {
        this.targetQuantity = newTargetQuantity;
    }

    public void buyStock(Long buyQuantity) {
        this.currentQuantity += buyQuantity;
    }

    public void sellStock(Long sellQuantity) {
        if(this.currentQuantity - sellQuantity < 0){
            throw new IllegalArgumentException("sell too much");
        }
        this.currentQuantity -= sellQuantity;
    }
}
