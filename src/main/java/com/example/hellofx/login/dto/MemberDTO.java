package com.example.hellofx.login.dto;

import com.example.hellofx.login.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long mno;

    private String id;

    private String pw;

    public Member toEntity(){ // dto -> entity
        Member member = Member.builder()
                .mno(this.mno)
                .id(this.id)
                .pw(this.pw)
                .build();

        return member;
    }
}
