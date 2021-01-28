package com.moneybook.moneybook.domain.moneybook;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MoneyBookRepositoryTest {

    @Autowired
    MoneyBookRepository moneyBookRepository;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanup() {
        moneyBookRepository.deleteAll();
    }

    @Test
    public void findByUsernameAndDateTest() {
        //given
        String username = "userTest";
        int year = 2021;
        int month = 1;

        Member member = memberRepository.save(Member.builder()
                .username(username)
                .password("pw")
                .email("test@test")
                .build());

        MoneyBook moneyBook = MoneyBook.builder()
                .member(member)
                .date(LocalDateTime.of(year, month, 1, 0, 0))
                .context("test context tt")
                .amount(10000L)
                .tag("test tag")
                .build();

        moneyBookRepository.save(moneyBook);

        //when
        MoneyBook findMoneyBook = moneyBookRepository.findByUsernameAndDate(username, year, month).get(0);

        //then
        System.out.println("moneyBook = " + moneyBook.getContext());
        System.out.println("findMoneyBook = " + findMoneyBook.getContext());

        assertThat(findMoneyBook.getId()).isEqualTo(moneyBook.getId());
    }
}