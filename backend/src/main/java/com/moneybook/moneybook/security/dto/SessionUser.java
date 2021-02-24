package com.moneybook.moneybook.security.dto;

import com.moneybook.moneybook.domain.member.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String username;
    private String password;
    private String email;

    public SessionUser(Member member) {
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.email = member.getEmail();
    }
}
