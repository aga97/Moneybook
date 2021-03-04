package com.moneybook.moneybook.dto.moneybook;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyBookReadByTagRequestDto {

    private String username;
    private String tag;

    @Builder
    public MoneyBookReadByTagRequestDto(String username, String tag) {
        this.username = username;
        this.tag = tag;
    }
}
