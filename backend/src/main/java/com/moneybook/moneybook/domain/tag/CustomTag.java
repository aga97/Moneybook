package com.moneybook.moneybook.domain.tag;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import com.moneybook.moneybook.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CustomTag extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String tag;

    @Builder
    public CustomTag(Member member, String tag) {
        this.member = member;
        this.tag = tag;
    }

    public void changeTag(String newTag) {
        this.tag = newTag;
    }
}
