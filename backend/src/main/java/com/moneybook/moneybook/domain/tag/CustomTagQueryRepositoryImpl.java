package com.moneybook.moneybook.domain.tag;

import com.moneybook.moneybook.domain.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.moneybook.moneybook.domain.member.QMember.member;
import static com.moneybook.moneybook.domain.tag.QCustomTag.customTag;

public class CustomTagQueryRepositoryImpl implements CustomTagQueryRepository{

    private final JPAQueryFactory queryFactory;

    public CustomTagQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CustomTag> findByUsername(String username) {
        return queryFactory
                .selectFrom(customTag)
                .join(customTag.member, member).fetchJoin()
                .where(customTag.member.username.eq(username))
                .fetch();
    }

    @Override
    public void delById(Long id){
        queryFactory
                .delete(customTag)
                .where(customTag.id.eq(id))
                .execute();
    }
}
