package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    public void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    void join() {
        //given
        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                .username("username")
                .password("pw")
                .email("email")
                .build();

        //when
        Long savedId = memberService.join(requestDto);

        //then
        assertThat(savedId).isEqualTo(memberRepository.findById(savedId).get().getId());
    }

    @Test
    void changePassword() {
        //given
        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
                .username("username")
                .password("pw")
                .email("email")
                .build();

        //when
        Long savedId = memberService.join(requestDto);
        memberService.changePassword(requestDto.getUsername(), "changedPassword");

        //then
        assertThat("changedPassword").isEqualTo(memberRepository.findById(savedId).get().getPassword());
    }
}