package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.stock.*;
import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import com.moneybook.moneybook.dto.stockinformation.StockInformationSaveRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalSaveRequestDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingReadRequestDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingReadResponseDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingSaveRequestDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StockTradingServiceTest {

    @Autowired
    StockTradingService stockTradingService;

    @Autowired
    MemberService memberService;

    @Autowired
    StockInformationService stockInformationService;

    @Autowired
    StockPersonalService stockPersonalService;

    @Autowired
    StockTradingRepository stockTradingRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StockInformationRepository stockInformationRepository;

    @Autowired
    StockPersonalRepository stockPersonalRepository;

    @BeforeEach
    public void init() {
        memberService.join(MemberSaveRequestDto.builder()
                .username("testStockTrading")
                .password("pw")
                .email("ttt")
                .build());
        stockInformationService.save(StockInformationSaveRequestDto.builder()
                .ticker("SPCE")
                .currency("USD")
                .currentPrice(32.9)
                .build());
        stockPersonalService.save(StockPersonalSaveRequestDto.builder()
                .username("testStockTrading")
                .ticker("SPCE")
                .targetQuantity(100L)
                .currentQuantity(50L)
                .build());
    }

    @AfterEach
    public void cleanup() {
        stockTradingRepository.deleteAll();
        stockPersonalRepository.deleteAll();
        stockInformationRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    public void saveAndFindByUsernameAndDate() {
        //given
        StockTradingSaveRequestDto saveRequestDto = StockTradingSaveRequestDto.builder()
                .username("testStockTrading")
                .ticker("SPCE")
                .stockQuantity(10L)
                .price(42L)
                .year(2021).month(1).day(1)
                .build();

        StockTradingReadRequestDto readRequestDto = StockTradingReadRequestDto.builder()
                .username("testStockTrading")
                .year(2021).month(1)
                .build();

        //when
        stockTradingService.save(saveRequestDto);

        List<StockTradingReadResponseDto> responseDtos = stockTradingService.findByUsernameAndDate(readRequestDto);

        //then
        assertThat(responseDtos.size()).isEqualTo(1);
        List<StockPersonal> stockPersonal = stockPersonalRepository.findByUsernameAndTicker("testStockTrading", "SPCE");

        //save 할 때 stockPersonal currentQuantity 수량 변화
        assertThat(stockPersonal.get(0).getCurrentQuantity()).isEqualTo(60);
    }

    @Test
    public void updateAll() throws Exception {
        //given
        StockTradingSaveRequestDto saveRequestDto = StockTradingSaveRequestDto.builder()
                .username("testStockTrading")
                .ticker("SPCE")
                .stockQuantity(10L)
                .price(42L)
                .year(2021).month(1).day(1)
                .build();
        Long saveId = stockTradingService.save(saveRequestDto);

        StockTradingUpdateRequestDto updateRequestDto = StockTradingUpdateRequestDto.builder()
                .id(saveId)
                .price(2000L)
                .stockQuantity(-10L)
                .year(2021).month(1).day(1)
                .build();

        //when
        stockTradingService.updateAll(updateRequestDto);

        StockTrading findStockTrading = stockTradingRepository.findById(saveId).get();

        //then
        assertThat(findStockTrading.getPrice()).isEqualTo(2000L);
        assertThat(findStockTrading.getStockQuantity()).isEqualTo(-10L);

        List<StockPersonal> stockPersonal = stockPersonalRepository.findByUsernameAndTicker("testStockTrading", "SPCE");
        assertThat(stockPersonal.get(0).getCurrentQuantity()).isEqualTo(40);
    }
}