package com.moneybook.moneybook.domain.moneybook;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class MoneyBook {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String username;

    private LocalDateTime date;

    private String context;

    @Column(nullable = false)
    private Long amount;

    private String tag;

    @Builder
    public MoneyBook(String username, LocalDateTime date, String context, Long amount, String tag) {
        this.username = username;
        this.date = date;
        this.context = context;
        this.amount = amount;
        this.tag = tag;
    }
}
