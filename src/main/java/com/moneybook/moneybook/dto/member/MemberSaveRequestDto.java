package com.moneybook.moneybook.dto.member;

import com.moneybook.moneybook.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberSaveRequestDto {
    private String username;
    private String password;
    private String email;
}
