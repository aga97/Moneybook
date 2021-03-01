package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.requestdto.CustomTagDto;
import com.moneybook.moneybook.dto.tag.CustomTagReadRequestDto;
import com.moneybook.moneybook.dto.tag.CustomTagReadResponseDto;
import com.moneybook.moneybook.dto.tag.CustomTagSaveRequestDto;
import com.moneybook.moneybook.security.token.AjaxAuthenticationToken;
import com.moneybook.moneybook.service.CustomTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;


@RequiredArgsConstructor
@RestController
public class CustomTagApiController {

    private final CustomTagService customTagService;

    @GetMapping("/api/v1/custom_tag")
    public List<CustomTagReadResponseDto> findByUsername(Principal principal) {
        CustomTagReadRequestDto dto = new CustomTagReadRequestDto(((AjaxAuthenticationToken) principal).getUsername());

        return customTagService.findAll(dto);
    }

    @PostMapping("/api/v1/custom_tag")
    public Long saveCustomTag(@RequestBody CustomTagDto requestDto, Principal principal) {

        CustomTagSaveRequestDto dto = CustomTagSaveRequestDto.builder()
                .username(((AjaxAuthenticationToken) principal).getUsername())
                .tag(requestDto.getTag())
                .build();

        return customTagService.save(dto);
    }

    @DeleteMapping("/api/v1/custom_tag/{id}")
    public Long deleteCustomTag(@PathVariable Long id) {
        customTagService.deleteCustomTag(id);
        return id;
    }
}
