package com.moneybook.moneybook.domain.moneybook;

import com.moneybook.moneybook.dto.moneybook.MoneyBookReadRequestDto;
import com.moneybook.moneybook.dto.moneybook.MoneyBookReadResponseDto;
import com.moneybook.moneybook.dto.moneybook.QMoneyBookReadResponseDto;
import com.moneybook.moneybook.exceptions.InvalidDateException;
import com.moneybook.moneybook.exceptions.InvalidUsernameException;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.moneybook.moneybook.domain.moneybook.QMoneyBook.moneyBook;

public class MoneyBookQueryRepositoryImpl implements MoneyBookQueryRepository{

    private final JPAQueryFactory queryFactory;

    public MoneyBookQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MoneyBookReadResponseDto> findByUsernameAndDate(MoneyBookReadRequestDto condition) {
        return queryFactory
                .select(new QMoneyBookReadResponseDto(
                        moneyBook.id,
                        moneyBook.date.dayOfMonth(),
                        moneyBook.context,
                        moneyBook.amount,
                        moneyBook.tag
                ))
                .from(moneyBook)
                .where(
                        usernameEq(condition.getUsername()),
                        yearMonthEq(condition.getYear(), condition.getMonth())
                )
                .fetch();
    }

    @Override
    public List<MoneyBook> findByUsernameAndDate(String username, Integer year, Integer month) {
        return queryFactory
                .selectFrom(moneyBook)
                .where(
                        usernameEq(username),
                        yearMonthEq(year, month)
                )
                .fetch();
    }

    private BooleanExpression usernameEq(String username){

        if(!StringUtils.hasText(username)){
            throw new InvalidUsernameException("not valid username. username=" + username);
        }

        return moneyBook.member.username.eq(username);
    }
    private BooleanExpression yearMonthEq(Integer year, Integer month){
        if(month < 1 || month > 12){
            throw new InvalidDateException("not valid month. month=" + month);
        }

        LocalDateTime firstDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime lastDate;
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||
        month == 10 || month == 12)
            lastDate = LocalDateTime.of(year, month, 31, 0, 0);
        else
            lastDate = LocalDateTime.of(year, month, 30, 0, 0);
        return moneyBook.date.between(firstDate, lastDate);
    }
}
