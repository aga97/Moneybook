package com.moneybook.moneybook.domain.moneybook;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import com.moneybook.moneybook.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MoneyBook extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime date;

    private String context;

    @Column(nullable = false)
    private Long amount;

    private String tag;

    @Builder
    public MoneyBook(Member member, LocalDateTime date, String context, Long amount, String tag) {
        this.member = member;
        this.date = date;
        this.context = context;
        this.amount = amount;
        this.tag = tag;
    }

    public void changeDate(LocalDateTime newDate) {
        this.date = newDate;
    }

    public void changeContext(String newContext) {
        this.context = newContext;
    }

    public void changeAmount(Long newAmount) {
        this.amount = newAmount;
    }

    public void changeTag(String newTag) { this.tag = newTag; }
}
