package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.stock.StockTradingRepository;
import com.moneybook.moneybook.dto.stocktrading.StockTradingSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class StockTradingService {

    private final StockTradingRepository stockTradingRepository;

    @Transactional
    public Long save(StockTradingSaveRequestDto stock) {
        return stockTradingRepository.save(stock.toEntity()).getId();
    }
}
