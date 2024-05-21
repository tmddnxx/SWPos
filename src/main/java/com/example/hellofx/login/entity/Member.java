package com.example.hellofx.login.entity;

import com.example.hellofx.login.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mno;

    @Column
    private String id;

    @Column
    private String pw;

    public MemberDTO toDTO() {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMno(this.mno);
        memberDTO.setId(this.id);
        memberDTO.setPw(this.pw);

        return memberDTO;
    }
}
