package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.domain.stock.StockPersonal;
import com.moneybook.moneybook.domain.stock.StockPersonalRepository;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalReadRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalReadResponseDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalSaveRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalUpdateRequestDto;
import com.moneybook.moneybook.service.StockPersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class StockPersonalApiController {

    private final StockPersonalService stockPersonalService;

    @GetMapping("/api/v1/stock_personal")
    public List<StockPersonalReadResponseDto> findByUsername(@RequestBody StockPersonalReadRequestDto requestDto) {
        return stockPersonalService.findByUsername(requestDto);
    }

    @PostMapping("/api/v1/stock_personal")
    public Long save(@RequestBody StockPersonalSaveRequestDto requestDto) {
        return stockPersonalService.save(requestDto);
    }

    @PutMapping("/api/v1/stock_personal")
    public Long update(@RequestBody StockPersonalUpdateRequestDto requestDto) {
        return stockPersonalService.updateAll(requestDto);
    }

    @DeleteMapping("/api/v1/stock_personal/{id}")
    public Long delete(@PathVariable Long id) {
        stockPersonalService.deleteStockPersonal(id);
        return id;
    }
}
