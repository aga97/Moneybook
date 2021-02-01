package com.moneybook.moneybook.dto.requestdto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DateDto {

    private Integer year;
    private Integer month;

    @Builder
    public DateDto(Integer year, Integer month) {
        this.year = year;
        this.month = month;
    }
}
