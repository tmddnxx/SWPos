package com.example.hellofx.login.service;


import com.example.hellofx.login.dto.MemberDTO;
import com.example.hellofx.login.entity.Member;
import com.example.hellofx.login.repository.MemberRepository;

public interface MemberService {

    Long login(MemberDTO memberDTO);
}
