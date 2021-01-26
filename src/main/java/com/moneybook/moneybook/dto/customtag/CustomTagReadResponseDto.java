package com.moneybook.moneybook.dto.customtag;

import com.moneybook.moneybook.domain.tag.CustomTag;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomTagReadResponseDto {
    private Long id;
    private String tag;

    public CustomTagReadResponseDto(CustomTag entity) {
        this.id = entity.getId();
        this.tag = entity.getTag();
    }
}
