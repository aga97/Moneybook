package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.requestdto.StockPersonalDto;
import com.moneybook.moneybook.dto.requestdto.StockPersonalUpdateDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalReadRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalReadResponseDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalSaveRequestDto;
import com.moneybook.moneybook.dto.stockpersonal.StockPersonalUpdateRequestDto;
import com.moneybook.moneybook.security.token.AjaxAuthenticationToken;
import com.moneybook.moneybook.service.StockPersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class StockPersonalApiController {

    private final StockPersonalService stockPersonalService;

    @GetMapping("/api/v1/stock_personal")
    public List<StockPersonalReadResponseDto> findByUsername(Principal principal) {
        StockPersonalReadRequestDto dto = new StockPersonalReadRequestDto(((AjaxAuthenticationToken) principal).getUsername());
        return stockPersonalService.findByUsername(dto);
    }

    @PostMapping("/api/v1/stock_personal")
    public Long save(@RequestBody StockPersonalDto requestDto, Principal principal) {
        StockPersonalSaveRequestDto dto = StockPersonalSaveRequestDto.builder()
                .username(((AjaxAuthenticationToken) principal).getUsername())
                .ticker(requestDto.getTicker())
                .targetQuantity(requestDto.getTargetQuantity())
                .currentQuantity(requestDto.getCurrentQuantity())
                .build();
        return stockPersonalService.save(dto);
    }

    //request dto id url로 변경 예정
    @PutMapping("/api/v1/stock_personal/{id}")
    public Long update(@PathVariable Long id, @RequestBody StockPersonalUpdateDto requestDto) {

        StockPersonalUpdateRequestDto dto = StockPersonalUpdateRequestDto.builder()
                .id(id)
                .targetQuantity(requestDto.getTargetQuantity())
                .currentQuantity(requestDto.getCurrentQuantityWeight())
                .build();

        return stockPersonalService.updateAll(dto);
    }

    @DeleteMapping("/api/v1/stock_personal/{id}")
    public Long delete(@PathVariable Long id) {
        stockPersonalService.deleteStockPersonal(id);
        return id;
    }
}
