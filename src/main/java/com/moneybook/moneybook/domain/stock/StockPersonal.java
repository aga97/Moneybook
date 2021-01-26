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

    private String username;
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
}
