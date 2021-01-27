package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class StockTradingRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StockInformationRepository stockInformationRepository;

    @Autowired
    StockTradingRepository stockTradingRepository;

    @BeforeEach
    public void init() {
        Member member1 = Member.builder()
                .username("testA")
                .password("pw")
                .email("test@test1")
                .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .username("testB")
                .password("pw")
                .email("test@test2")
                .build();
        memberRepository.save(member2);

        StockInformation stock1 = StockInformation.builder()
                .ticker("SPCE")
                .currency("USD")
                .currentPrice(52.5)
                .build();
        stockInformationRepository.save(stock1);

        StockInformation stock2 = StockInformation.builder()
                .ticker("MSFT")
                .currency("USD")
                .currentPrice(52.5)
                .build();
        stockInformationRepository.save(stock2);

        StockTrading trading1 = StockTrading.builder()
                .member(member1)
                .stockInformation(stock1)
                .price(1000L)
                .stockQuantity(10L)
                .tradingDate(LocalDateTime.of(2021, 1, 1, 0, 0))
                .build();
        stockTradingRepository.save(trading1);

        StockTrading trading2 = StockTrading.builder()
                .member(member1)
                .stockInformation(stock2)
                .price(1000L)
                .stockQuantity(10L)
                .tradingDate(LocalDateTime.of(2021, 1, 1, 0, 0))
                .build();
        stockTradingRepository.save(trading2);

        StockTrading trading3 = StockTrading.builder()
                .member(member2)
                .stockInformation(stock1)
                .price(1000L)
                .stockQuantity(10L)
                .tradingDate(LocalDateTime.of(2021, 2, 1, 0, 0))
                .build();
        stockTradingRepository.save(trading3);

        StockTrading trading4 = StockTrading.builder()
                .member(member2)
                .stockInformation(stock2)
                .price(1000L)
                .stockQuantity(10L)
                .tradingDate(LocalDateTime.of(2021, 2, 1, 0, 0))
                .build();
        stockTradingRepository.save(trading4);
    }

    @Test
    public void findByUsernameAndDate() throws Exception {
        //given
        String username = "testA";
        Integer year = 2021;
        Integer month = 1;

        //when
        List<StockTrading> byUsernameAndDate = stockTradingRepository.findByUsernameAndDate(username, year, month);

        //then
        Assertions.assertThat(byUsernameAndDate.size()).isEqualTo(2);
    }
}