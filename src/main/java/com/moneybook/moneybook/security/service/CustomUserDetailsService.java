package com.moneybook.moneybook.security.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Member> findMember = memberRepository.findByUsername(username);
        if(findMember.isEmpty()){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        Member member = findMember.get(0);

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(member.getRole()));

        MemberContext accountContext = new MemberContext(member, roles);

        return accountContext;
    }
}