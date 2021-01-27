package com.moneybook.moneybook.domain.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.List;


@SpringBootTest
class StockInformationRepositoryTest {

    @Autowired
    StockInformationRepository stockInformationRepository;

    @AfterEach
    public void cleanup() {
        stockInformationRepository.deleteAll();
    }

    @Test
    public void findByTickerTest(){
        //given
        StockInformation stock = StockInformation.builder()
                .ticker("SPCE")
                .currentPrice(30.2)
                .currency("USD")
                .build();

        stockInformationRepository.save(stock);

        //when
        List<StockInformation> findStock = stockInformationRepository.findByTicker("SPCE");

        //then
        Assertions.assertThat(findStock.get(0).getId()).isEqualTo(stock.getId());
    }
}