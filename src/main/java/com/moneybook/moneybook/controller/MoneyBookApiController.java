package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.domain.moneybook.MoneyBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MoneyBookApiController {
    private final MoneyBookRepository moneyBookRepository;

}
