package com.moneybook.moneybook.domain.stock;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.moneybook.moneybook.domain.stock.QStockPersonal.stockPersonal;
import static com.moneybook.moneybook.domain.stock.QStockTrading.stockTrading;

public class StockTradingQueryRepositoryImpl implements StockTradingQueryRepository{

    private final JPAQueryFactory queryFactory;

    public StockTradingQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<StockTrading> findByUsernameAndDate(String username, Integer year, Integer month) {
        return queryFactory
                .selectFrom(stockTrading)
                .join(stockTrading.stockPersonal, stockPersonal)
                .where(
                        usernameEq(username),
                        yearMonthEq(year, month)
                )
                .fetch();
    }

    private BooleanExpression usernameEq(String username){
        return StringUtils.hasText(username) ? stockTrading.stockPersonal.member.username.eq(username) : null;
    }
    private BooleanExpression yearMonthEq(Integer year, Integer month){
        if(month < 1 || month > 12){
            throw new IllegalArgumentException("out of bounce month=" + month);
        }

        LocalDateTime firstDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime lastDate;
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||
                month == 10 || month == 12)
            lastDate = LocalDateTime.of(year, month, 31, 0, 0);
        else
        if(month == 2){
            lastDate = LocalDateTime.of(year, month, 28, 0, 0);
        }
        else
            lastDate = LocalDateTime.of(year, month, 30, 0, 0);

        return stockTrading.tradingDate.between(firstDate, lastDate);
    }

}
