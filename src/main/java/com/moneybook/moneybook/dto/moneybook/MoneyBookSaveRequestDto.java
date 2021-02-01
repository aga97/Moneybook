package com.moneybook.moneybook.dto.moneybook;

import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.moneybook.MoneyBook;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MoneyBookSaveRequestDto {
    private String username;
    private String context;
    private Long amount;
    private String tag;
    private Integer year;
    private Integer month;
    private Integer day;

    @Builder
    public MoneyBookSaveRequestDto(String username, String context, Long amount, String tag, Integer year, Integer month, Integer day) {
        this.username = username;
        this.context = context;
        this.amount = amount;
        this.tag = tag;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
