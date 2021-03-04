package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.moneybook.MoneyBook;
import com.moneybook.moneybook.domain.moneybook.MoneyBookRepository;
import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import com.moneybook.moneybook.dto.moneybook.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MoneyBookServiceTest {

    @Autowired
    MoneyBookService moneyBookService;

    @Autowired
    MemberService memberService;

    @Autowired
    MoneyBookRepository moneyBookRepository;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void init() {
        memberService.join(MemberSaveRequestDto.builder()
                .username("testMoneyBook")
                .password("pw")
                .email("ttt")
                .build());
    }

    @AfterEach
    public void cleanup() {
        moneyBookRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void saveAndFindAll() {
        //given
        String username = "testMoneyBook";
        MoneyBookSaveRequestDto saveRequestDto = MoneyBookSaveRequestDto.builder()
                .username(username)
                .amount(1000L)
                .context("test context")
                .tag("test tag")
                .year(2021)
                .month(1)
                .day(1)
                .build();

        MoneyBookReadByDateRequestDto readRequestDto = MoneyBookReadByDateRequestDto.builder()
                .username(username)
                .year(2021)
                .month(1)
                .build();
        //when
        Long savedId = moneyBookService.save(saveRequestDto);
        List<MoneyBookReadByDateResponseDto> all = moneyBookService.findAll(readRequestDto);

        //then
        assertThat(all.size()).isEqualTo(1);
        assertThat(savedId).isEqualTo(all.get(0).getId());
    }

    @Test
    void findMinMaxDate() {
        //given
        String username = "testMoneyBook";
        MoneyBookSaveRequestDto saveRequestDto = MoneyBookSaveRequestDto.builder()
                .username(username)
                .amount(1000L)
                .context("test context")
                .tag("test tag")
                .year(2020)
                .month(3)
                .day(1)
                .build();
        moneyBookService.save(saveRequestDto);

        MoneyBookSaveRequestDto saveRequestDto2 = MoneyBookSaveRequestDto.builder()
                .username(username)
                .amount(1000L)
                .context("test context")
                .tag("test tag")
                .year(2021)
                .month(1)
                .day(1)
                .build();
        moneyBookService.save(saveRequestDto2);

        //when
        MoneyBookDateResponseDto responseDto = moneyBookService.findMinMaxDateByUsername(MoneyBookDateRequestDto.builder()
                .username(username)
                .build());
        //then
        assertThat(responseDto.getMinYear()).isEqualTo(2020);
        assertThat(responseDto.getMaxYear()).isEqualTo(2021);
        assertThat(responseDto.getMinMonth()).isEqualTo(3);
        assertThat(responseDto.getMaxMonth()).isEqualTo(1);
    }

    @Test
    void EmptyMoneyBookFindMinMaxDate() {
        //given
        String username = "testMoneyBook";
        //when
        MoneyBookDateResponseDto responseDto = moneyBookService.findMinMaxDateByUsername(MoneyBookDateRequestDto.builder()
                .username(username)
                .build());
        //then
        assertThat(responseDto.getMinYear()).isEqualTo(null);
        assertThat(responseDto.getMaxYear()).isEqualTo(null);
        assertThat(responseDto.getMinMonth()).isEqualTo(null);
        assertThat(responseDto.getMaxMonth()).isEqualTo(null);
    }

    @Test
    void updateAll() {
        //given
        String username = "testMoneyBook";
        MoneyBookSaveRequestDto saveRequestDto = MoneyBookSaveRequestDto.builder()
                .username(username)
                .amount(1000L)
                .context("test context")
                .tag("test tag")
                .year(2021)
                .month(1)
                .day(1)
                .build();
        Long savedId = moneyBookService.save(saveRequestDto);

        MoneyBookUpdateRequestDto requestDto = MoneyBookUpdateRequestDto.builder()
                .id(savedId)
                .amount(1000L)
                .context("test context2")
                .tag("test tag2")
                .year(2020)
                .month(2)
                .day(2)
                .build();
        //when
        Long updateId = moneyBookService.updateAll(requestDto);
        MoneyBook moneyBook = moneyBookRepository.findById(updateId).get();

        //then
        assertThat(moneyBook.getAmount()).isEqualTo(requestDto.getAmount());
        assertThat(moneyBook.getContext()).isEqualTo(requestDto.getContext());
        assertThat(moneyBook.getTag()).isEqualTo(requestDto.getTag());
        assertThat(moneyBook.getDate().getYear()).isEqualTo(requestDto.getYear());
        assertThat(moneyBook.getDate().getMonthValue()).isEqualTo(requestDto.getMonth());
        assertThat(moneyBook.getDate().getDayOfMonth()).isEqualTo(requestDto.getDay());

    }

    @Test
    void deleteMoneyBook() {
        //given
        String username = "testMoneyBook";
        MoneyBookSaveRequestDto saveRequestDto = MoneyBookSaveRequestDto.builder()
                .username(username)
                .amount(1000L)
                .context("test context")
                .tag("test tag")
                .year(2021)
                .month(1)
                .day(1)
                .build();
        Long saveId = moneyBookService.save(saveRequestDto);

        //when
        moneyBookService.deleteMoneyBook(saveId);

        //then
        assertThat(moneyBookRepository.findById(saveId).isEmpty()).isTrue();
    }
}