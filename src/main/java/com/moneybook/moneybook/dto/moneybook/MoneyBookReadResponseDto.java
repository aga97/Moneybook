package com.moneybook.moneybook.dto.moneybook;

import com.moneybook.moneybook.domain.moneybook.MoneyBook;
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

    @Builder
    public MoneyBookReadResponseDto(MoneyBook entity) {
        this.id = entity.getId();
        this.day = entity.getDate().getDayOfMonth();
        this.context = entity.getContext();
        this.amount = entity.getAmount();
        this.tag = entity.getTag();
    }
}
