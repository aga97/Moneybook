package com.moneybook.moneybook.service;

import com.moneybook.moneybook.domain.member.Member;
import com.moneybook.moneybook.domain.member.MemberRepository;
import com.moneybook.moneybook.domain.tag.CustomTag;
import com.moneybook.moneybook.domain.tag.CustomTagRepository;
import com.moneybook.moneybook.dto.tag.CustomTagReadRequestDto;
import com.moneybook.moneybook.dto.tag.CustomTagReadResponseDto;
import com.moneybook.moneybook.dto.tag.CustomTagSaveRequestDto;
import com.moneybook.moneybook.exceptions.InvalidUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomTagService {

    private final CustomTagRepository customTagRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(CustomTagSaveRequestDto requestDto) {

        List<Member> findMember = memberRepository.findByUsername(requestDto.getUsername());
        if(findMember.isEmpty()) throw new InvalidUsernameException("not exist username. check username=" + requestDto.getUsername());

        CustomTag customTagEntity = CustomTag.builder()
                .member(findMember.get(0))
                .tag(requestDto.getTag())
                .build();

        return customTagRepository.save(customTagEntity).getId();
    }

    @Transactional(readOnly = true)
    public List<CustomTagReadResponseDto> findAll(CustomTagReadRequestDto requestDto) {

        List<CustomTag> customTags = customTagRepository.findByUsername(requestDto.getUsername());
        List<CustomTagReadResponseDto> responseDto = new ArrayList<>();

        for (CustomTag customTag : customTags) {
            responseDto.add(new CustomTagReadResponseDto(customTag));
        }

        return responseDto;
    }

    @Transactional
    public void deleteCustomTag(Long id) { customTagRepository.deleteById(id); }

}
