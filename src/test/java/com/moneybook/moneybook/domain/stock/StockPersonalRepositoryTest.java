package com.moneybook.moneybook.domain.stock;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockPersonalRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StockInformationRepository stockInformationRepository;

    @Autowired
    StockPersonalRepository stockPersonalRepository;

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
        stockPersonalRepository.save(stockPersonal2);

        StockPersonal stockPersonal3 = StockPersonal.builder()
                .member(member2)
                .stockInformation(stock1)
                .targetQuantity(100L)
                .currentQuantity(10L)
                .build();
        stockPersonalRepository.save(stockPersonal3);

        StockPersonal stockPersonal4 = StockPersonal.builder()
                .member(member2)
                .stockInformation(stock2)
                .targetQuantity(100L)
                .currentQuantity(10L)
                .build();
        stockPersonalRepository.save(stockPersonal4);
    }

    @Test
    public void findByTickerTest() {

        //given
        String ticker = "SPCE";

        //when
        List<StockPersonal> byTicker = stockPersonalRepository.findByTicker(ticker);

        //then
        assertThat(byTicker.size()).isEqualTo(2);
    }

    @Test
    public void findByUsernameTest() {
        //given
        String username = "testA";

        //when
        List<StockPersonal> byUsername = stockPersonalRepository.findByUsername(username);

        //then
        assertThat(byUsername.size()).isEqualTo(2);
    }

    @Test
    public void findByUsernameAndTickerTest() throws Exception {
        //given
        String username = "testA";
        String ticker = "SPCE";

        //when
        List<StockPersonal> byUsernameAndTicker = stockPersonalRepository.findByUsernameAndTicker(username, ticker);

        //then
        assertThat(byUsernameAndTicker.size()).isEqualTo(1);
    }
}