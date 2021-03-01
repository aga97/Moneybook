package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.requestdto.StockTradingDto;
import com.moneybook.moneybook.dto.requestdto.StockTradingUpdateDto;
import com.moneybook.moneybook.dto.stocktrading.*;
import com.moneybook.moneybook.security.token.AjaxAuthenticationToken;
import com.moneybook.moneybook.service.StockTradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(originPatterns = "*")
public class StockTradingApiController {

    private final StockTradingService stockTradingService;

    @GetMapping("/api/v1/stock_trading/{year}/{month}")
    public List<StockTradingReadResponseDto> findByUsernameAndDate(@PathVariable Integer year, @PathVariable Integer month, Principal principal) {

        StockTradingReadRequestDto dto = StockTradingReadRequestDto.builder()
                .username(((AjaxAuthenticationToken) principal).getUsername())
                .year(year)
                .month(month)
                .build();

        return stockTradingService.findByUsernameAndDate(dto);
    }

    @GetMapping("/api/v1/stock_trading/{ticker}")
    public List<StockTradingReadResponseDto> findByUsernameAndTicker(@PathVariable String ticker, Principal principal) {

        StockTradingReadByTickerRequestDto dto = StockTradingReadByTickerRequestDto.builder()
                .username(((AjaxAuthenticationToken) principal).getUsername())
                .ticker(ticker)
                .build();

        return stockTradingService.findByUsernameAndTicker(dto);
    }

    @PostMapping("/api/v1/stock_trading")
    public Long saveStockTrading(@RequestBody StockTradingDto requestDto, Principal principal){

        StockTradingSaveRequestDto dto = StockTradingSaveRequestDto.builder()
                .username(((AjaxAuthenticationToken) principal).getUsername())
                .ticker(requestDto.getTicker())
                .year(requestDto.getYear())
                .month(requestDto.getMonth())
                .day(requestDto.getDay())
                .price(requestDto.getPrice())
                .stockQuantity(requestDto.getStockQuantity())
                .build();

        return stockTradingService.save(dto);
    }

    //request dto id url로 변경 예정
    @PutMapping("/api/v1/stock_trading/{id}")
    public Long updateStockTrading(@PathVariable Long id, @RequestBody StockTradingUpdateDto requestDto) {

        StockTradingUpdateRequestDto dto = StockTradingUpdateRequestDto.builder()
                .id(id)
                .year(requestDto.getYear()).month(requestDto.getMonth()).day(requestDto.getDay())
                .price(requestDto.getPrice())
                .stockQuantity(requestDto.getStockQuantity())
                .build();

        return stockTradingService.updateAll(dto);
    }

    @DeleteMapping("/api/v1/stock_trading/{id}")
    public Long deleteStockTrading(@PathVariable Long id){
        stockTradingService.deleteStockTrading(id);
        return id;
    }


}
