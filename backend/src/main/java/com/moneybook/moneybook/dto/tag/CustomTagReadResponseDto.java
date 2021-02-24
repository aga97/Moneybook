package com.moneybook.moneybook.dto.tag;

import com.moneybook.moneybook.domain.tag.CustomTag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomTagReadResponseDto {
    private Long id;
    private String tag;

    public CustomTagReadResponseDto(CustomTag customTag) {
        this.id = customTag.getId();
        this.tag = customTag.getTag();
    }
}
