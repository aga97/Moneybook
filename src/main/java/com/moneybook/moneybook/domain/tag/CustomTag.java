package com.moneybook.moneybook.domain.tag;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class CustomTag extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String tag;

    @Builder
    public CustomTag(String username, String tag) {
        this.username = username;
        this.tag = tag;
    }

    public void changeTag(String newTag) {
        this.tag = newTag;
    }
}
