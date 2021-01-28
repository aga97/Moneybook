package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.stock.StockInformationRepository;
import com.moneybook.moneybook.dto.stockinformation.StockInformationSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class StockInformationServiceTest {

    @Autowired
    StockInformationService stockInformationService;

    @Autowired
    StockInformationRepository stockInformationRepository;

    @AfterEach
    public void cleanup() {
        stockInformationRepository.deleteAll();
    }

    @Test
    public void save() {
        //given
        StockInformationSaveRequestDto requestDto = StockInformationSaveRequestDto.builder()
                .ticker("SPCE")
                .currency("USD")
                .currentPrice(32.9)
                .build();
        //when
        Long saveId = stockInformationService.save(requestDto);

        //then
        assertThat(saveId).isEqualTo(stockInformationRepository.findById(saveId).get().getId());
    }

    @Test
    public void delete() {
        //given
        StockInformationSaveRequestDto requestDto = StockInformationSaveRequestDto.builder()
                .ticker("SPCE")
                .currency("USD")
                .currentPrice(32.9)
                .build();
        //when
        Long saveId = stockInformationService.save(requestDto);
        stockInformationService.deleteStockInformation("SPCE");

        //then
        assertThat(stockInformationRepository.findByTicker("SPCE").isEmpty()).isTrue();
    }
}