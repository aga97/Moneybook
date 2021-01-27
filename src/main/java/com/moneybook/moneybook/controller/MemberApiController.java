package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import com.moneybook.moneybook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/join")
    public Long join(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
        return memberService.join(memberSaveRequestDto);
    }
}
