package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.moneybook.MoneyBook;
import com.moneybook.moneybook.domain.moneybook.MoneyBookRepository;
import com.moneybook.moneybook.dto.moneybook.*;
import com.moneybook.moneybook.exceptions.InvalidIdException;
import com.moneybook.moneybook.exceptions.InvalidUsernameException;
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
    public Long save(MoneyBookSaveRequestDto requestDto) {

        List<Member> findMember = memberRepository.findByUsername(requestDto.getUsername());
        if(findMember.isEmpty()) throw new InvalidUsernameException("not exist username. check username=" + requestDto.getUsername());

        MoneyBook moneyBookEntity = MoneyBook.builder()
                .member(findMember.get(0))
                .date(LocalDateTime.of(requestDto.getYear(), requestDto.getMonth(), requestDto.getDay(), 0, 0))
                .context(requestDto.getContext())
                .amount(requestDto.getAmount())
                .tag(requestDto.getTag())
                .build();

        return moneyBookRepository.save(moneyBookEntity).getId();
    }

    @Transactional(readOnly = true)
    public List<MoneyBookReadResponseDto> findAll(MoneyBookReadRequestDto requestDto){

        List<MoneyBook> moneyBooks = moneyBookRepository.findByUsernameAndDate(requestDto.getUsername(), requestDto.getYear(), requestDto.getMonth());

        List<MoneyBookReadResponseDto> responseDto = new ArrayList<>();

        for (MoneyBook moneyBook : moneyBooks) {
            responseDto.add(new MoneyBookReadResponseDto(moneyBook));
        }

        return responseDto;
    }

    @Transactional(readOnly = true)
    public MoneyBookDateResponseDto findMinMaxDateByUsername(MoneyBookDateRequestDto requestDto) {

        List<Member> findMember = memberRepository.findByUsername(requestDto.getUsername());
        if(findMember.isEmpty()) throw new InvalidUsernameException("not exist username. check username=" + requestDto.getUsername());

        List<LocalDateTime> minMaxDate = moneyBookRepository.findMinMaxDateByUsername(requestDto.getUsername());
        if(minMaxDate.isEmpty())
            return MoneyBookDateResponseDto.builder().build();

        return MoneyBookDateResponseDto.builder()
                .minYear(minMaxDate.get(0).getYear())
                .minMonth(minMaxDate.get(0).getMonthValue())
                .maxYear(minMaxDate.get(1).getYear())
                .maxMonth(minMaxDate.get(1).getMonthValue())
                .build();
    }

    @Transactional
    public Long updateAll(MoneyBookUpdateRequestDto requestDto){
        MoneyBook moneyBook = moneyBookRepository.findById(requestDto.getId())
                .orElseThrow(() -> new InvalidIdException("not exist id. check id=" + requestDto.getId()));

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
                .orElseThrow(() -> new InvalidIdException("not exist id. check id=" + id));
        moneyBook.changeContext(context);
    }

    @Transactional
    public void updateAmount(Long id, Long amount) {
        MoneyBook moneyBook = moneyBookRepository.findById(id)
                .orElseThrow(() -> new InvalidIdException("not exist id. check id=" + id));
        moneyBook.changeAmount(amount);
    }

    @Transactional
    public void deleteMoneyBook(Long id) {
        moneyBookRepository.deleteById(id);
    }

}
