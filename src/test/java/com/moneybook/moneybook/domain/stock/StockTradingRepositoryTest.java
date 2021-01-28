package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class StockTradingRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StockInformationRepository stockInformationRepository;

    @Autowired
    StockPersonalRepository stockPersonalRepository;

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

        StockPersonal stockPersonal1 = StockPersonal.builder()
                .member(member1)
                .stockInformation(stock1)
                .targetQuantity(100L)
                .currentQuantity(10L)
                .build();
        stockPersonalRepository.save(stockPersonal1);

        StockPersonal stockPersonal2 = StockPersonal.builder()
                .member(member1)
                .stockInformation(stock2)
                .targetQuantity(100L)
                .currentQuantity(10L)
                .build();
        stockPersonalRepository.save(stockPersonal1);

        for(int i=0; i<10; i++) {
            StockTrading trading = StockTrading.builder()
                    .stockPersonal(stockPersonal1)
                    .price(1000L)
                    .stockQuantity(10L)
                    .tradingDate(LocalDateTime.of(2021, i+1, 1, 0, 0))
                    .build();
            stockTradingRepository.save(trading);
        }
    }

    @AfterEach
    public void cleanup() {
        stockTradingRepository.deleteAll();
        stockPersonalRepository.deleteAll();
        memberRepository.deleteAll();
        stockInformationRepository.deleteAll();
    }

    @Test
    public void findByUsernameAndDate(){
        //given
        String username = "testA";
        Integer year = 2021;
        Integer month = 1;

        //when
        List<StockTrading> byUsernameAndDate = stockTradingRepository.findByUsernameAndDate(username, year, month);

        //then
        Assertions.assertThat(byUsernameAndDate.size()).isEqualTo(1);
    }
}