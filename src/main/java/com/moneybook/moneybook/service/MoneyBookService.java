package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.moneybook.MoneyBook;
import com.moneybook.moneybook.domain.moneybook.MoneyBookRepository;
import com.moneybook.moneybook.dto.moneybook.MoneyBookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MoneyBookService {

    private final MoneyBookRepository moneyBookRepository;

    @Transactional
    public Long save(MoneyBookSaveRequestDto moneyBook) {
        return moneyBookRepository.save(moneyBook.toEntity()).getId();
    }

    @Transactional
    public void updateContext(Long id, String context) {
        MoneyBook moneyBook = moneyBookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exist row"));
        moneyBook.changeContext(context);
    }

    @Transactional
    public void updateAmount(Long id, Long amount) {
        MoneyBook moneyBook = moneyBookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exist row"));
        moneyBook.changeAmount(amount);
    }

    @Transactional
    public void deleteMoneyBook(Long id) {
        moneyBookRepository.delete(moneyBookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exist row")));
    }

}
