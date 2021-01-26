package com.moneybook.moneybook.dto.moneybook;

import com.moneybook.moneybook.domain.moneybook.MoneyBook;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MoneyBookSaveRequestDto {

    private String username;
    private Integer year;
    private Integer month;
    private Integer day;
    private String context;

    @Builder
    public MoneyBookSaveRequestDto(String username, Integer year, Integer month, Integer day, String context) {
        this.username = username;
        this.year = year;
        this.month = month;
        this.day = day;
        this.context = context;
    }

    public MoneyBook toEntity() {

        return MoneyBook.builder()
                .username(username)
                .date(LocalDateTime.of(year, month, day, 0, 0))
                .context(context)
                .build();
    }
}
