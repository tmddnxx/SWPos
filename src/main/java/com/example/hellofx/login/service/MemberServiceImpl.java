package com.example.hellofx.login.service;


import com.example.hellofx.login.dto.MemberDTO;
import com.example.hellofx.login.entity.Member;
import com.example.hellofx.login.repository.MemberRepository;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Long login(MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();

        return memberRepository.login(member.getId(), member.getPw());
    }
}
