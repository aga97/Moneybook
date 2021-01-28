package com.moneybook.moneybook.controller;

import com.moneybook.moneybook.dto.tag.CustomTagReadRequestDto;
import com.moneybook.moneybook.dto.tag.CustomTagReadResponseDto;
import com.moneybook.moneybook.dto.tag.CustomTagSaveRequestDto;
import com.moneybook.moneybook.service.CustomTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class CustomTagApiController {

    private final CustomTagService customTagService;

    @GetMapping("api/v1/custom_tag")
    public List<CustomTagReadResponseDto> findByUsername(@RequestBody CustomTagReadRequestDto requestDto) {
        return customTagService.findAll(requestDto);
    }

    @PostMapping("/api/v1/custom_tag")
    public Long saveCustomTag(@RequestBody CustomTagSaveRequestDto requestDto) {
        return customTagService.save(requestDto);
    }

    @DeleteMapping("/api/v1/custim_tag/{id}")
    public Long deleteCustomTag(@PathVariable Long id) {
        customTagService.deleteCustomTag(id);
        return id;
    }
}
