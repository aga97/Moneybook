package com.moneybook.moneybook.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneybook.moneybook.dto.member.MemberSaveRequestDto;
import com.moneybook.moneybook.security.token.AjaxAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    //login url
    public AjaxLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/api/v1/login"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        MemberSaveRequestDto memberDto = objectMapper.readValue(request.getReader(), MemberSaveRequestDto.class);
        if(!StringUtils.hasLength(memberDto.getUsername()) || !StringUtils.hasLength(memberDto.getPassword())){
            throw new IllegalArgumentException("Username or Passoword is empty");
        }

        AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken(memberDto.getUsername(), memberDto.getPassword());

        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }
}