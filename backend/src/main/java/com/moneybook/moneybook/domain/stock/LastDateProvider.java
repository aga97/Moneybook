package com.moneybook.moneybook.domain.stock;

import java.time.LocalDateTime;

public class LastDateProvider {

    public static LocalDateTime lastDate(Integer year, Integer month) {

        boolean monthCondition = month == 1 || month == 3 || month == 5 || month == 7 ||
                month == 8 || month == 10 || month == 12;

        //leap year
        if((year%400==0)||(year%4==0&&year%100!=0)) {
            if(monthCondition) {
                return LocalDateTime.of(year, month, 31, 0, 0);
            } else if(month == 2){
                return LocalDateTime.of(year, month, 29, 0, 0);
            }
            else {
                return LocalDateTime.of(year, month, 30, 0, 0);
            }
        }
        else {
            if(monthCondition) {
                return LocalDateTime.of(year, month, 31, 0, 0);
            } else if(month == 2){
                return LocalDateTime.of(year, month, 28, 0, 0);
            }
            else {
                return LocalDateTime.of(year, month, 30, 0, 0);
            }
        }
    }
}
