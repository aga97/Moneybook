package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.moneybook.MoneyBook;
import com.moneybook.moneybook.domain.moneybook.MoneyBookRepository;
import com.moneybook.moneybook.dto.moneybook.MoneyBookReadRequestDto;
import com.moneybook.moneybook.dto.moneybook.MoneyBookReadResponseDto;
import com.moneybook.moneybook.dto.moneybook.MoneyBookSaveRequestDto;
import com.moneybook.moneybook.dto.moneybook.MoneyBookUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class MoneyBookService {

    private final MoneyBookRepository moneyBookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long save(MoneyBookSaveRequestDto moneyBook) {

        List<Member> findMember = memberRepository.findByUsername(moneyBook.getUsername());
        if(findMember.isEmpty()) throw new IllegalArgumentException("not exist username");

        MoneyBook moneyBookEntity = MoneyBook.builder()
                .member(findMember.get(0))
                .date(LocalDateTime.of(moneyBook.getYear(), moneyBook.getMonth(), moneyBook.getDay(), 0, 0))
                .context(moneyBook.getContext())
                .amount(moneyBook.getAmount())
                .tag(moneyBook.getTag())
                .build();

        return moneyBookRepository.save(moneyBookEntity).getId();
    }

    @Transactional(readOnly = true)
    public List<MoneyBookReadResponseDto> findAll(MoneyBookReadRequestDto requestDto){
        return moneyBookRepository.findByUsernameAndDate(requestDto);
    }

    @Transactional
    public Long updateAll(MoneyBookUpdateRequestDto requestDto){
        MoneyBook moneyBook = moneyBookRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("not exist row"));

        moneyBook.changeDate(LocalDateTime.of(requestDto.getYear(), requestDto.getMonth(),
                requestDto.getDay(), 0, 0));
        moneyBook.changeAmount(requestDto.getAmount());
        moneyBook.changeContext(requestDto.getContext());
        moneyBook.changeTag(requestDto.getTag());

        return moneyBook.getId();
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
