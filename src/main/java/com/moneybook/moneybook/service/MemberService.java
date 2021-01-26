package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberSaveRequestDto member) {

        Member memberEntity = Member.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .build();
        validateDuplicateMember(memberEntity);
        return memberRepository.save(memberEntity).getId();
    }

    @Transactional(readOnly = true)
    private void validateDuplicateMember(Member member){

        List<Member> findMembers = memberRepository.findByUsername(member.getUsername());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("already exist member");
        }
    }

    @Transactional
    public void changePassword(String username, String password) {
        List<Member> findMember = memberRepository.findByUsername(username);
        if(findMember.isEmpty()) {
            throw new IllegalStateException("not exist member");
        }
        findMember.get(0).changePassword(password);
    }
}
