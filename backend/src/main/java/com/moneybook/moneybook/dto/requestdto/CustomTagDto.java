package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomTagDto {
    private String tag;

    @Builder
    public CustomTagDto(String tag) {
        this.tag = tag;
    }
}
