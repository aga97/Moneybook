package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.moneybook.*;
import com.moneybook.moneybook.dto.requestdto.MoneyBookDto;
import com.moneybook.moneybook.dto.requestdto.MoneyBookUpdateDto;
import com.moneybook.moneybook.security.token.AjaxAuthenticationToken;
import com.moneybook.moneybook.service.MoneyBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(originPatterns = "*")
public class MoneyBookApiController {

    private final MoneyBookService moneyBookService;

    @GetMapping("/api/v1/date")
    public MoneyBookDateResponseDto findMinMaxDateByUsername(Principal principal){

        MoneyBookDateRequestDto dto = new MoneyBookDateRequestDto(((AjaxAuthenticationToken) principal).getUsername());

        return moneyBookService.findMinMaxDateByUsername(dto);
    }

    @GetMapping("/api/v1/moneybook/{year}/{month}")
    public List<MoneyBookReadByDateResponseDto> findByUsername(@PathVariable Integer year, @PathVariable Integer month, Principal principal) {
        MoneyBookReadByDateRequestDto dto = MoneyBookReadByDateRequestDto.builder()
                .username(((AjaxAuthenticationToken) principal).getUsername())
                .year(year)
                .month(month)
                .build();

        return moneyBookService.findAll(dto);
    }

    @GetMapping("/api/v1/moneybook/{tag}")
    public List<MoneyBookReadByTagResponseDto> findByTag(@PathVariable String tag, Principal principal) {
        MoneyBookReadByTagRequestDto dto = MoneyBookReadByTagRequestDto.builder()
                .username(((AjaxAuthenticationToken) principal).getUsername())
                .tag(tag)
                .build();

        return moneyBookService.findByTag(dto);
    }

    @PostMapping("/api/v1/moneybook")
    public Long saveMoneyBook(@RequestBody MoneyBookDto requestDto, Principal principal) {

        MoneyBookSaveRequestDto dto = MoneyBookSaveRequestDto.builder()
                .username(((AjaxAuthenticationToken) principal).getUsername())
                .year(requestDto.getYear()).month(requestDto.getMonth()).day(requestDto.getDay())
                .tag(requestDto.getTag())
                .context(requestDto.getContext())
                .amount(requestDto.getAmount())
                .build();

        return moneyBookService.save(dto);
    }

    @PutMapping("/api/v1/moneybook/{id}")
    public Long updateMoneyBook(@PathVariable Long id, @RequestBody MoneyBookUpdateDto requestDto) {

        MoneyBookUpdateRequestDto dto = MoneyBookUpdateRequestDto.builder()
                .id(id)
                .year(requestDto.getYear()).month(requestDto.getMonth()).day(requestDto.getDay())
                .context(requestDto.getContext())
                .amount(requestDto.getAmount())
                .tag(requestDto.getTag())
                .build();

        return moneyBookService.updateAll(dto);
    }

    @DeleteMapping("/api/v1/moneybook/{id}")
    public Long deleteMoneyBook(@PathVariable Long id) {
        moneyBookService.deleteMoneyBook(id);
        return id;
    }
}
