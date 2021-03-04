package com.moneybook.moneybook.domain.moneybook;

import com.moneybook.moneybook.domain.stock.LastDateProvider;
import com.moneybook.moneybook.dto.moneybook.MoneyBookReadByDateRequestDto;
import com.moneybook.moneybook.dto.moneybook.MoneyBookReadByDateResponseDto;
import com.moneybook.moneybook.dto.moneybook.QMoneyBookReadByDateResponseDto;
import com.moneybook.moneybook.exceptions.InvalidDateException;
import com.moneybook.moneybook.exceptions.InvalidUsernameException;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.moneybook.moneybook.domain.member.QMember.member;
import static com.moneybook.moneybook.domain.moneybook.QMoneyBook.moneyBook;

public class MoneyBookQueryRepositoryImpl implements MoneyBookQueryRepository{

    private final JPAQueryFactory queryFactory;

    public MoneyBookQueryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MoneyBookReadByDateResponseDto> findByUsernameAndDate(MoneyBookReadByDateRequestDto condition) {
        return queryFactory
                .select(new QMoneyBookReadByDateResponseDto(
                        moneyBook.id,
                        moneyBook.context,
                        moneyBook.amount,
                        moneyBook.tag,
                        moneyBook.date.dayOfMonth()
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

    @Override
    public List<LocalDateTime> findMinMaxDateByUsername(String username) {
        Tuple dateTimes = queryFactory
                .select(moneyBook.date.min(), moneyBook.date.max())
                .from(moneyBook)
                .join(moneyBook.member, member)
                .where(moneyBook.member.username.eq(username))
                .fetchOne();

        List<LocalDateTime> minMaxDates = new ArrayList<>();

        //if dateTimes is empty, return empty List
        if(dateTimes.get(moneyBook.date.min()) == null){
            System.out.println("send empty list");
            return minMaxDates;
        }

        minMaxDates.add(dateTimes.get(moneyBook.date.min()));
        minMaxDates.add(dateTimes.get(moneyBook.date.max()));
        return minMaxDates;
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
        LocalDateTime lastDate = LastDateProvider.lastDate(year, month);

        return moneyBook.date.between(firstDate, lastDate);
    }

    public List<MoneyBook> findByUsernameAndTag(String username, String tag) {
        return queryFactory
                .selectFrom(moneyBook)
                .where(
                        moneyBook.member.username.eq(username),
                        moneyBook.tag.eq(tag)
                )
                .fetch();
    }
}
