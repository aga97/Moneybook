package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.member.QMember;
import com.moneybook.moneybook.exceptions.InvalidIdException;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.moneybook.moneybook.domain.member.QMember.member;
import static com.moneybook.moneybook.domain.stock.QStockInformation.stockInformation;
import static com.moneybook.moneybook.domain.stock.QStockPersonal.stockPersonal;
import static com.moneybook.moneybook.domain.stock.QStockTrading.*;


public class StockPersonalQueryRepositoryImpl implements StockPersonalQueryRepository{

    private final JPAQueryFactory queryFactory;
    StockPersonalQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<StockPersonal> findByTicker(String ticker) {

        return queryFactory
                .selectFrom(stockPersonal)
                .join(stockPersonal.stockInformation, stockInformation).fetchJoin()
                .where(stockPersonal.stockInformation.ticker.eq(ticker))
                .fetch();
    }

    @Override
    public List<StockPersonal> findByUsername(String username) {
        return queryFactory
                .selectFrom(stockPersonal)
                .join(stockPersonal.member, member).fetchJoin()
                .where(stockPersonal.member.username.eq(username))
                .fetch();
    }

    @Override
    public List<StockPersonal> findByUsernameAndTicker(String username, String ticker) {
        return queryFactory
                .selectFrom(stockPersonal)
                .join(stockPersonal.member, member).fetchJoin()
                .join(stockPersonal.stockInformation, stockInformation).fetchJoin()
                .where(
                        stockPersonal.member.username.eq(username),
                        stockPersonal.stockInformation.ticker.eq(ticker)
                )
                .fetch();
    }

    @Override
    public void delById(Long id){
        queryFactory
                .delete(stockPersonal)
                .where(stockPersonal.id.eq(id))
                .execute();
    }
}
