package com.moneybook.moneybook.dto.moneybook;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyBookDateRequestDto {
    private String username;

    @Builder
    public MoneyBookDateRequestDto(String username) {
        this.username = username;
    }
}
