package com.moneybook.moneybook.dto.moneybook;

import com.moneybook.moneybook.domain.moneybook.MoneyBook;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class MoneyBookReadResponseDto {

    private Long id;
    private Integer day;
    private String context;
    private Long amount;
    private String tag;

    @QueryProjection
    public MoneyBookReadResponseDto(Long id, Integer day, String context, Long amount, String tag) {
        this.id = id;
        this.day = day;
        this.context = context;
        this.amount = amount;
        this.tag = tag;
    }

    public MoneyBookReadResponseDto(MoneyBook entity) {
        this.id = entity.getId();
        this.day = entity.getDate().getDayOfMonth();
        this.context = entity.getContext();
        this.amount = entity.getAmount();
        this.tag = entity.getTag();
    }
}
