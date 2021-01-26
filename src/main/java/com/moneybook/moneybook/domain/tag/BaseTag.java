package com.moneybook.moneybook.domain.tag;

import com.moneybook.moneybook.domain.BaseTimeEntity;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BaseTag extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String tag;

    @Builder
    public BaseTag(String tag) {
        this.tag = tag;
    }
}
