package com.moneybook.moneybook.domain.stock;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.moneybook.moneybook.domain.stock.QStockInformation.stockInformation;
import static com.moneybook.moneybook.domain.stock.QStockPersonal.stockPersonal;

public class StockPersonalQueryRepositoryImpl implements StockPersonalQueryRepository{

    private final JPAQueryFactory queryFactory;
    StockPersonalQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<StockPersonal> findByTicker(String ticker) {

        return queryFactory
                .selectFrom(stockPersonal)
                .join(stockPersonal.stockInformation, stockInformation)
                .where(stockPersonal.stockInformation.ticker.eq(ticker))
                .fetch();
    }
}
