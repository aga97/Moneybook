package com.moneybook.moneybook.dto.customtag;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomTagSaveRequestDto {
    private String username;
    private String tag;

    @Builder
    public CustomTagSaveRequestDto(String username, String tag) {
        this.username = username;
        this.tag = tag;
    }
}
