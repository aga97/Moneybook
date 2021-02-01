package com.moneybook.moneybook.domain.moneybook;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MoneyBookRepositoryTest {

    @Autowired
    MoneyBookRepository moneyBookRepository;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void init() {

    }

    @AfterEach
    public void cleanup() {
        moneyBookRepository.deleteAll();
        memberRepository.deleteAll();
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

    @Test
    void findMinMaxDateTest() {
        //given
        String username = "userTest";
        int year = 2021;
        int month = 5;
        LocalDateTime time = LocalDateTime.of(year, month, 1, 0, 0);

        Member member = memberRepository.save(Member.builder()
                .username(username)
                .password("pw")
                .email("test@test")
                .build());

        MoneyBook moneyBook = MoneyBook.builder()
                .member(member)
                .date(time)
                .context("test context tt")
                .amount(10000L)
                .tag("test tag")
                .build();

        moneyBookRepository.save(moneyBook);

        int year2 = 2022;
        int month2 = 2;
        LocalDateTime time2 = LocalDateTime.of(year2, month2, 1, 0, 0);

        MoneyBook moneyBook2 = MoneyBook.builder()
                .member(member)
                .date(time2)
                .context("test context tt")
                .amount(10000L)
                .tag("test tag")
                .build();

        moneyBookRepository.save(moneyBook2);

        //when
        List<LocalDateTime> findDate = moneyBookRepository.findMinMaxDateByUsername(username);

        //then
        assertThat(findDate).containsExactly(time, time2);
    }
}