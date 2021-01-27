package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.stocktrading.StockTradingReadRequestDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingReadResponseDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingSaveRequestDto;
import com.moneybook.moneybook.dto.stocktrading.StockTradingUpdateRequestDto;
import com.moneybook.moneybook.service.StockTradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class StockTradingApiController {

    private final StockTradingService stockTradingService;

    @GetMapping("/api/v1/stock_trading")
    public List<StockTradingReadResponseDto> findByUsernameAndDate(@RequestBody StockTradingReadRequestDto requestDto) {
        return stockTradingService.findByUsernameAndDate(requestDto);
    }

    @PostMapping("/api/v1/stock_trading")
    public Long saveStockTrading(@RequestBody StockTradingSaveRequestDto requestDto){
        return stockTradingService.save(requestDto);
    }

    @PutMapping("/api/v1/stock_trading")
    public Long updateStockTrading(@RequestBody StockTradingUpdateRequestDto requestDto) {
        return stockTradingService.updateAll(requestDto);
    }

    @DeleteMapping("/api/v1/stock_trading/{id}")
    public Long deleteStockTrading(@PathVariable Long id){
        stockTradingService.deleteStockTrading(id);
        return id;
    }


}
