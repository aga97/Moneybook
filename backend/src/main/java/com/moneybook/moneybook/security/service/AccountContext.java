package com.moneybook.moneybook.security.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.security.dto.SessionUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountContext extends User{

    private final SessionUser sessionUser;

    public AccountContext(SessionUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.sessionUser = user;
    }

    public SessionUser getMember() {
        return sessionUser;
    }
}
