package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.moneybook.*;
import com.moneybook.moneybook.service.MoneyBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class MoneyBookApiController {

    private final MoneyBookService moneyBookService;

    @GetMapping("/api/v1/date")
    public MoneyBookDateResponseDto findMinMaxDateByUsername(@RequestBody MoneyBookDateRequestDto moneyBookDateRequestDto){
        return moneyBookService.findMinMaxDateByUsername(moneyBookDateRequestDto);
    }

    @GetMapping("/api/v1/moneybook")
    public List<MoneyBookReadResponseDto> findByUsername(@RequestBody MoneyBookReadRequestDto requestDto) {
        return moneyBookService.findAll(requestDto);
    }

    @PostMapping("/api/v1/moneybook")
    public Long saveMoneyBook(@RequestBody MoneyBookSaveRequestDto requestDto) {
        return moneyBookService.save(requestDto);
    }

    @PutMapping("/api/v1/moneybook")
    public Long updateMoneyBook(@RequestBody MoneyBookUpdateRequestDto requestDto) {
        return moneyBookService.updateAll(requestDto);
    }

    @DeleteMapping("/api/v1/moneybook/{id}")
    public Long deleteMoneyBook(@PathVariable Long id) {
        moneyBookService.deleteMoneyBook(id);
        return id;
    }
}
