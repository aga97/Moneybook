package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import com.moneybook.moneybook.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/v1/join")
    public Long join(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
        memberSaveRequestDto.setPassword(passwordEncoder.encode(memberSaveRequestDto.getPassword()));
        return memberService.join(memberSaveRequestDto);
    }
}
