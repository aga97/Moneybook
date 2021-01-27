package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.stock.*;
import com.moneybook.moneybook.dto.stocktrading.StockTradingReadRequestDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingReadResponseDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingSaveRequestDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class StockTradingService {

    private final StockTradingRepository stockTradingRepository;
    private final StockPersonalRepository stockPersonalRepository;

    @Transactional
    public Long save(StockTradingSaveRequestDto requestDto) {

        List<StockPersonal> stockPersonal = stockPersonalRepository.findByUsernameAndTicker(requestDto.getUsername(), requestDto.getTicker());
        if(stockPersonal.isEmpty()) {throw new IllegalArgumentException("not exist stock_personal");}

        Long tradingQuantity = requestDto.getStockQuantity();
        stockPersonal.get(0).tradeCurrentQuantity(tradingQuantity);

        StockTrading stockTrading = StockTrading.builder()
                .stockPersonal(stockPersonal.get(0))
                .price(tradingQuantity)
                .stockQuantity(requestDto.getStockQuantity())
                .tradingDate(LocalDateTime.of(requestDto.getYear(), requestDto.getMonth(), requestDto.getDay(), 0, 0))
                .build();


        return stockTradingRepository.save(stockTrading).getId();
    }

    @Transactional(readOnly = true)
    public List<StockTradingReadResponseDto> findByUsernameAndDate(StockTradingReadRequestDto requestDto) {
        List<StockTrading> findStockTradings = stockTradingRepository.findByUsernameAndDate(requestDto.getUsername(), requestDto.getYear(), requestDto.getMonth());

        List<StockTradingReadResponseDto> responseDtos = new ArrayList<>();

        for (StockTrading findStockTrading : findStockTradings) {
            responseDtos.add(new StockTradingReadResponseDto(findStockTrading));
        }

        return responseDtos;
    }

    @Transactional
    public Long updateAll(StockTradingUpdateRequestDto requestDto) {
        StockTrading stockTrading = stockTradingRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("not exist row"));

        stockTrading.changeTradingPrice(requestDto.getPrice());
        stockTrading.changeStockQuantity(requestDto.getStockQuantity());
        stockTrading.changeTradingDate(LocalDateTime.of(requestDto.getYear(), requestDto.getMonth(), requestDto.getDay(), 0, 0));

        return stockTrading.getId();
    }

    @Transactional
    public Long deleteStockTrading(Long id) {
        StockTrading stockTrading = stockTradingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exist row"));

        Long tradingQuantity = stockTrading.getStockQuantity();
        stockTrading.getStockPersonal().tradeCurrentQuantity(-tradingQuantity);

        stockTradingRepository.deleteById(id);
        return id;
    }
}
