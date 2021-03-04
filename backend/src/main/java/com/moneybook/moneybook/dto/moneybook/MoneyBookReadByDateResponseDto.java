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
public class MoneyBookReadByDateResponseDto {

    private Long id;
    private String context;
    private Long amount;
    private String tag;
    private Integer day;

    @QueryProjection
    public MoneyBookReadByDateResponseDto(Long id, String context, Long amount, String tag, Integer day) {
        this.id = id;
        this.context = context;
        this.amount = amount;
        this.tag = tag;
        this.day = day;
    }

    public MoneyBookReadByDateResponseDto(MoneyBook entity) {
        this.id = entity.getId();
        this.context = entity.getContext();
        this.amount = entity.getAmount();
        this.tag = entity.getTag();
        this.day = entity.getDate().getDayOfMonth();
    }
}
