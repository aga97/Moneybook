package com.moneybook.moneybook.dto.tag;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomTagReadRequestDto {
    private String username;

    @Builder
    public CustomTagReadRequestDto(String username) {
        this.username = username;
    }
}
