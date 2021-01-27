package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.stock.StockInformation;
import com.moneybook.moneybook.domain.stock.StockInformationRepository;
import com.moneybook.moneybook.domain.stock.StockPersonal;
import com.moneybook.moneybook.domain.stock.StockPersonalRepository;
import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import com.moneybook.moneybook.dto.stockinformation.StockInformationSaveRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalReadRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalReadResponseDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalSaveRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockPersonalServiceTest {

    @Autowired
    StockPersonalService stockPersonalService;

    @Autowired
    MemberService memberService;

    @Autowired
    StockInformationService stockInformationService;

    @Autowired
    StockPersonalRepository stockPersonalRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    StockInformationRepository stockInformationRepository;

    @BeforeEach
    public void init() {
        memberService.join(MemberSaveRequestDto.builder()
                .username("testStockPersonal")
                .password("pw")
                .email("ttt")
                .build());
        stockInformationService.save(StockInformationSaveRequestDto.builder()
                .ticker("SPCE")
                .currency("USD")
                .currentPrice(32.9)
                .build());
    }

    @AfterEach
    public void cleanup() {
        stockPersonalRepository.deleteAll();
        memberRepository.deleteAll();
        stockInformationRepository.deleteAll();
    }

    @Test
    public void saveAndFindByUsername(){
        //given
        StockPersonalSaveRequestDto saveRequestDto = StockPersonalSaveRequestDto.builder()
                .username("testStockPersonal")
                .ticker("SPCE")
                .targetQuantity(500L)
                .currentQuantity(50L)
                .build();

        StockPersonalReadRequestDto readRequestDto = StockPersonalReadRequestDto.builder()
                .username("testStockPersonal")
                .build();

        //when
        Long saveId = stockPersonalService.save(saveRequestDto);
        List<StockPersonalReadResponseDto> responseDtos = stockPersonalService.findByUsername(readRequestDto);

        //then
        assertThat(responseDtos.size()).isEqualTo(1);
        assertThat(responseDtos.get(0).getTicker()).isEqualTo("SPCE");
    }

    @Test
    public void updateAll() throws Exception {
        //given
        StockPersonalSaveRequestDto saveRequestDto = StockPersonalSaveRequestDto.builder()
                .username("testStockPersonal")
                .ticker("SPCE")
                .targetQuantity(500L)
                .currentQuantity(50L)
                .build();
        Long saveId = stockPersonalService.save(saveRequestDto);

        StockPersonalUpdateRequestDto updateDto = StockPersonalUpdateRequestDto.builder()
                .id(saveId)
                .targetQuantity(1000L)
                .currentQuantity(100L)
                .build();

        //when
        Long updateId = stockPersonalService.updateAll(updateDto);
        StockPersonal stockPersonal = stockPersonalRepository.findById(updateId).get();

        //then
        assertThat(stockPersonal.getTargetQuantity()).isEqualTo(1000L);
        assertThat(stockPersonal.getCurrentQuantity()).isEqualTo(150);
    }

    @Test
    public void deleteStockPersonal() throws Exception {
        //given
        StockPersonalSaveRequestDto saveRequestDto = StockPersonalSaveRequestDto.builder()
                .username("testStockPersonal")
                .ticker("SPCE")
                .targetQuantity(500L)
                .currentQuantity(50L)
                .build();
        Long saveId = stockPersonalService.save(saveRequestDto);

        //when
        stockPersonalService.deleteStockPersonal(saveId);

        //then
        assertThat(stockPersonalRepository.findById(saveId).isEmpty()).isTrue();
    }
}